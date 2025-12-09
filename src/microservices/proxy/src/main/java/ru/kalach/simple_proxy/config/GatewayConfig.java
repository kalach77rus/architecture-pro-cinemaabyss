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
                        .path("/api/movies/**")
                        .and()
                        .weight("movies", 100 - gatewayProperties.getMoviesMigrationPercent())
                        .uri(gatewayProperties.getMonolithUrl())
                )
                .route("cinema-micro", r -> r
                        .path("/api/movies/**")
                        .and()
                        .weight("movies", gatewayProperties.getMoviesMigrationPercent())
                        .uri(gatewayProperties.getMoviesUrl())
                )
                .route("api-default", r -> r
                    .path("/api/**")
                    .uri(gatewayProperties.getMonolithUrl())
                )
                .build();
    }
}
