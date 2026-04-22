package com.nitin.order_service.client;

import com.nitin.order_service.dto.InventoryRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceClient {
    private final  InventoryClient inventoryClient;

    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallBack")
    @Retry(name="inventory")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<Boolean> checkStock(List<InventoryRequest> inventoryRequestList){
        return CompletableFuture.supplyAsync(()->{
            return inventoryClient.isInStock(inventoryRequestList);
        });
//        return inventoryClient.isInStock(inventoryRequestList);
    }

    public CompletableFuture<Boolean> inventoryFallBack(List<InventoryRequest> inventoryRequestList, Throwable ex){
        log.error("inventory service is down");
        return CompletableFuture.completedFuture(false);
    }
}
