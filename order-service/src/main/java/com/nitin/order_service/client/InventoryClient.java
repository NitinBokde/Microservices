package com.nitin.order_service.client;

import com.nitin.order_service.dto.InventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "inventory", url = "http://localhost:8081")
public interface InventoryClient {

    @PostMapping("/api/inventory")
    boolean isInStock(@RequestBody List<InventoryRequest> inventoryRequestList);
}
