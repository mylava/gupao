//package com.gupao.edu.gateway.filter;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.ServerWebExchangeDecorator;
//
///**
// * comment:
// *
// * @author: lipengfei
// * @date: 2020/5/6
// */
//public class PayloadServerWebExchangeDecorator extends ServerWebExchangeDecorator {
//
//    private PartnerServerHttpRequestDecorator requestDecorator;
//
//    private PartnerServerHttpResponseDecorator responseDecorator;
//
//    public PayloadServerWebExchangeDecorator(ServerWebExchange delegate) {
//        super(delegate);
//        requestDecorator = new PartnerServerHttpRequestDecorator(delegate.getRequest());
//        responseDecorator = new PartnerServerHttpResponseDecorator(delegate.getResponse());
//    }
//
//    @Override
//    public ServerHttpRequest getRequest() {
//        return requestDecorator;
//    }
//
//    @Override
//    public ServerHttpResponse getResponse() {
//        return responseDecorator;
//    }
//}
