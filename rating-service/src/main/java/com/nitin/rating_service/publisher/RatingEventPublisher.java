package com.nitin.rating_service.publisher;

import com.nitin.rating_service.config.RabbitMQConfig;
import com.nitin.rating_service.config.RabbitMQProperties;
import com.nitin.rating_service.dto.RatingEvent;
import com.nitin.rating_service.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingEventPublisher {

    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    public void publishRatingEvent(RatingEvent event){
        log.info("sending event : {}", event);
        rabbitTemplate.convertAndSend(rabbitMQProperties.getExchange(),
                rabbitMQProperties.getRoutingKey(),
                event);

    }
}
