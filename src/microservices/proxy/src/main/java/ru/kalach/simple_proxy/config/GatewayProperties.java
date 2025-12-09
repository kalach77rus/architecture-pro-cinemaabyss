package ru.kalach.simple_proxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api")
@Data
public class GatewayProperties {

    private String monolithUrl;

    private String moviesUrl;

    private String eventsUrl;

    private boolean gradualMigration;

    private int moviesMigrationPercent;

}
