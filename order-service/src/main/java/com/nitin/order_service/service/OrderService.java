package com.nitin.order_service.service;

import com.nitin.order_service.client.InventoryClient;
import com.nitin.order_service.client.InventoryServiceClient;
import com.nitin.order_service.dto.InventoryRequest;
import com.nitin.order_service.dto.OrderLineItemsDto;
import com.nitin.order_service.dto.OrderRequest;
import com.nitin.order_service.model.Order;
import com.nitin.order_service.model.OrderLineItems;
import com.nitin.order_service.respository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final InventoryServiceClient inventoryServiceClient;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        List<InventoryRequest> inventoryRequestList = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDtoInventoryRequest).toList();

        boolean isInStock = false;
        try {
            isInStock = inventoryServiceClient.checkStock(inventoryRequestList).get();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        }
        if(!isInStock){
            log.info("product out of stock");
            return "out of stock";
        }
        order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);
        return "Order placed succesfully";
    }

//    @CircuitBreaker(name="inventory",fallbackMethod = "inventoryFallBack")
//    public boolean isInStockCB(List<InventoryRequest> inventoryRequestList) {
//        return inventoryClient.isInStock(inventoryRequestList);
//    }
//    public boolean inventoryFallBack(List<InventoryRequest> inventoryRequestList, Throwable ex) {
//        log.error("Inventory service down: {}", ex.getMessage());
//        return false;
//    }
//    public boolean inventoryFallBack(List<InventoryRequest> inventoryRequestList, Throwable ex){
//        System.out.println("Inventory service is down");
//        log.error("inventory service is down : {}",ex.getMessage());
//        return false;
//    }

    private InventoryRequest mapToDtoInventoryRequest(OrderLineItemsDto orderLineItemsDto){
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setQuantity(orderLineItemsDto.getQuantity());
        inventoryRequest.setSkuCode(orderLineItemsDto.getSkuCode());
        return inventoryRequest;
    }
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setSkuCode((orderLineItemsDto.getSkuCode()));
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;
    }
}
