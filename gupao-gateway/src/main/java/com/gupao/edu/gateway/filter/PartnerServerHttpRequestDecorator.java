package com.gupao.edu.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

import static reactor.core.scheduler.Schedulers.single;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/5/6
 */
@Slf4j
public class PartnerServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private Flux<DataBuffer> body;
    private Long length;

    PartnerServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
        final String path = delegate.getURI().getPath();
        final String query = delegate.getURI().getQuery();
        final String method = Optional.ofNullable(delegate.getMethod()).orElse(HttpMethod.GET).name();
        final String head = delegate.getHeaders().entrySet()
                .stream()
                .map(entry -> "            " + entry.getKey() + ": [" + String.join(";", entry.getValue()) + "]")
                .collect(Collectors.joining("\n"));
        final MediaType contentType = delegate.getHeaders().getContentType();
        if (log.isDebugEnabled()) {
            log.debug("\n" +
                    "HttpMethod : {}\n" +
                    "Uri        : {}\n" +
                    "Headers    : \n" +
                    "{}", method, path + (StringUtils.isEmpty(query) ? "" : "?" + query), head);
        }
        length = delegate.getHeaders().getContentLength();
        Flux<DataBuffer> flux = super.getBody();
        if (LogUtils.legalLogMediaTypes.contains(contentType)) {
            body = flux
                    .publishOn(single())
                    .map(this::doAuth)
                    .map(dataBuffer -> LogUtils.loggingRequest(log, dataBuffer));
        } else {
            body = flux;
        }
    }

    private DataBuffer doAuth(DataBuffer buffer) {
        try {
            InputStream is = buffer.asInputStream();
            String oldBody = IOUtils.toString(is, "utf8");
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

            // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
            length = Long.valueOf(newBody.getBytes().length);

            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            return nettyDataBufferFactory.wrap(newBody.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return body;
    }

    public Long getContentLength() {
        return length;
    }
}
