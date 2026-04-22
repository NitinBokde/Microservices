package com.nitin.inventory.service;

import com.nitin.inventory.InventoryApplication;
import com.nitin.inventory.dto.InventoryRequest;
import com.nitin.inventory.model.Inventory;
import com.nitin.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(List<InventoryRequest> inventoryRequestList){

        List<String> skuCodes = inventoryRequestList.stream().map(InventoryRequest::getSkuCode).toList();
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes);

        System.out.println(inventoryList.toString());

        Map<String,Inventory> map = inventoryList.stream().collect(Collectors.toMap(Inventory::getSkuCode,i->i));

        return inventoryRequestList.stream().allMatch(item->{
            if(map.containsKey(item.getSkuCode())){
                if(map.get(item.getSkuCode()).getQuantity() >= item.getQuantity() ){
                    return true;
                }
            }
            return false;
        });



        //  Inefficient because we are making n database call based on length


//        for(InventoryRequest item : inventoryRequestList){
//            Inventory inventory = inventoryRepository.findBySkuCode(item.getSkuCode());
//            if(inventory==null || item.getQuantity()>inventory.getQuantity()){
//                System.out.println(item.getSkuCode() + " is not available");
//                return false;
//            }
//        }
//        return true;


    }
}
