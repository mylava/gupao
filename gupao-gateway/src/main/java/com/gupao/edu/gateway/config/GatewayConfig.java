//package com.gupao.edu.gateway.config;
//
//import com.fasterxml.jackson.core.filter.TokenFilter;
//import com.gupao.edu.gateway.filter.PayloadServerWebExchangeDecorator;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.server.WebFilter;
//
///**
// * comment:
// *
// * @author: lipengfei
// * @date: 30/03/2020
// */
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE) //过滤器顺序
//    public WebFilter webFilter(){
//        return (exchange, chain) -> chain.filter(new PayloadServerWebExchangeDecorator(exchange));
//    }
//
//
////    @Bean
////    public RouteLocator gupaoRouteLocator(RouteLocatorBuilder builder) {
////        return builder.routes()
////                .route("gupao-lotus",
////                        r -> r.path("/image/png")
////                                .filters(f ->
////                                        f.addResponseHeader("X-TestHeader", "foobar"))
////                                .uri("lb://gupao-lotus"))
////                .build();
////    }
//}
