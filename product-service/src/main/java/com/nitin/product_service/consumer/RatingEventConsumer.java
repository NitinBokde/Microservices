package com.nitin.product_service.consumer;

import com.nitin.product_service.event.RatingEvent;
import com.nitin.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class RatingEventConsumer {

    private final ProductService productService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(RatingEvent event){
        if(event == null || event.getProductSkuCode()==null){
            log.error("Invalid event received : {}",event);
            return;
        }
        log.info("Event received : {}", event);

        productService.updateAverageRating(event.getProductSkuCode(),event.getRatingValue(),event.getOldRatingValue());
    }
}
