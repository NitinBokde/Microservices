package com.nitin.product_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitMQConfig {
    private final RabbitMQProperties rabbitMQProperties;

    @Bean
     public Queue queue(){
         return new Queue(rabbitMQProperties.getQueue(),true);
     }

     @Bean
     public TopicExchange exchange(){
         return new TopicExchange(rabbitMQProperties.getExchange());
     }

     @Bean
     public Binding binding(Queue queue, TopicExchange topicExchange){
         return BindingBuilder
                 .bind(queue)
                 .to(topicExchange)
                 .with(rabbitMQProperties.getRoutingKey());
     }

     @Bean
     public MessageConverter messageConverter(){
         return new Jackson2JsonMessageConverter();
     }
}
