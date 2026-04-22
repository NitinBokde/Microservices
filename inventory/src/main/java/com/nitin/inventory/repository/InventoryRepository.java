package com.nitin.inventory.repository;

import com.nitin.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    Optional<Inventory> findBySkuCodeAndQuantity(String skuCode, Integer quantity);

    Inventory findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
