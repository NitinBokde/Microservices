package com.nitin.product_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {
    private String exchange;
    private String queue;
    private String routingKey;
}
