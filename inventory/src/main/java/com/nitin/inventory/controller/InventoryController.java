package com.nitin.inventory.controller;

import com.nitin.inventory.dto.InventoryRequest;
import com.nitin.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean isInStock(@RequestBody List<InventoryRequest> inventoryRequestList){
        return inventoryService.isInStock(inventoryRequestList);
    }
}
