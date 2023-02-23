package com.gupao.edu.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static reactor.core.scheduler.Schedulers.single;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 30/03/2020
 */
@Slf4j
@Component
@RefreshScope
@ConfigurationProperties(prefix = "token")
public class TokenFilter implements GlobalFilter, Ordered {

    //    @Value("${token.skipAuthUrls}")
    private String[] skipAuthUrls;

    @Autowired
    private UserAuthFacade userAuthFacade;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        return chain.filter(exchange);
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String uriString = serverHttpRequest.getURI().toString();
        log.info("request uri {} ", uriString);//打印每次请求的uri

        String url = exchange.getRequest().getURI().getPath();
        //跳过不需要验证的路径
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)) {
            return chain.filter(exchange);
        }

        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        ServerHttpResponse resp = exchange.getResponse();
        if (StringUtils.isBlank(token)) {
            //没有token
            return authErro(resp, "there is no token");
        } else {
            //有token
//            Long userId = userAuthFacade.validateToken(token);
            Long userId = 1l;
            if (null == userId) {
                return authErro(resp, "invalid token");
            }
            return addParameterToBody(userId.toString(), exchange, chain);
        }
    }


    private Mono<Void> addParameterToBody(String parameter, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        // mediaType
        MediaType mediaType = serverHttpRequest.getHeaders().getContentType();
        HttpMethod method = serverHttpRequest.getMethod();

        // read & modify body
        if (HttpMethod.POST == method) {
            AtomicReference<String> bodyRef = new AtomicReference<>();
            HttpHeaders headers = new HttpHeaders();

            CountDownLatch countDownLatch = new CountDownLatch(1);
            serverHttpRequest.getBody()
                    .map(buffer -> {
                        InputStream is = buffer.asInputStream();
                        String oldBody = null;
                        try {
                            oldBody = IOUtils.toString(is, "utf8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String newBody = null;
                        if (!StringUtils.isEmpty(oldBody)) {
                            JSONObject oldBodyJson = JSONObject.parseObject(oldBody);
                            oldBodyJson.put("userUniqueCode", "3366");
                            newBody = oldBodyJson.toJSONString();
                        } else {
                            JSONObject newBodyJson = new JSONObject();
                            newBodyJson.put("userUniqueCode", "3366");
                            newBody = newBodyJson.toJSONString();
                        }
                        return newBody;
                    })
                    .doOnSubscribe(subscription -> subscription.request(1))
                    .doOnComplete(countDownLatch::countDown)
                    .subscribe(s -> {
                        headers.putAll(exchange.getRequest().getHeaders());
                        // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                        long length = s.getBytes().length;
                        headers.remove(HttpHeaders.CONTENT_LENGTH);
                        headers.setContentLength(length);
                        log.info(">>>>>>>>>> {}", JSONObject.toJSONString(exchange.getRequest().getHeaders()));
                        log.info("<<<<<<<<<< {}", JSONObject.toJSONString(headers));
                        bodyRef.set(s);
                        log.info("----------- {}", bodyRef.get());

                    });
            //等待异步执行完成,不然header和body可能没有值
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ServerHttpRequest newRequest = serverHttpRequest.mutate().uri(serverHttpRequest.getURI()).build();
            newRequest = new ServerHttpRequestDecorator(newRequest) {
                @Override
                public HttpHeaders getHeaders() {
                    return headers;
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    byte[] bytes = bodyRef.get().getBytes(StandardCharsets.UTF_8);
                    NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
                    DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
                    buffer.write(bytes);
                    return Flux.just(buffer);
                }
            };
            return chain.filter(exchange.mutate().request(newRequest).build());
        } else if (HttpMethod.GET == method) {
            URI uri = exchange.getRequest().getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();
            if (org.springframework.util.StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }

            // 添加参数
            query.append("userUniqueCode=").append(parameter);

            // 替换参数
            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(query.toString())
                    .build(true)
                    .toUri();

            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(request).build());
        }
        return chain.filter(exchange);
    }

    /**
     * 字符串转DataBuffer
     *
     * @param value
     * @return
     */
    private DataBuffer string2Buffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    /**
     * 认证错误输出
     *
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer buffer = resp.bufferFactory()
                .wrap(Result.fail(CodeMessage.ILLEGAL_TOKEN_ERROR.fillArgs(mess))
                        .toString().getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
