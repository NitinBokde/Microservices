package com.nitin.rating_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(rabbitMQProperties.getExchange());
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
