package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Router {

    private final JwtFilter jwtFilter;

    public Router(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/auth/**")
                                .uri("lb://auth"))
                .route(r ->
                        r.path("/product/**")
                                .uri("lb://product-service"))
                .route(r ->
                        r.path("/cart/**")
                                .filters(f -> f.filter(jwtFilter))
                                .uri("lb://cart-service"))
                .route(r ->
                        r.path("/order/**")
                                .filters(f -> f.filter(jwtFilter))
                                .uri("lb://order-service"))
                .build();
    }
}
