package ru.kalach.simple_proxy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.random.RandomGenerator;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(GatewayProperties.class)
public class GatewayConfig {

    private final GatewayProperties gatewayProperties;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("api-mono", r -> r
                        .weight("movies", 100 - gatewayProperties.getMoviesMigrationPercent())
                        .and()
                        .path("/api/**")
                        .uri(gatewayProperties.getMonolithUrl())
                )
                .route("cinema-micro", r -> r
                        .weight("movies", gatewayProperties.getMoviesMigrationPercent())
                        .and()
                        .path("/api/**")
                        .uri(gatewayProperties.getMoviesUrl())
                )
                .build();
    }
}
