package com.nitin.product_service.repository;

import com.nitin.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    Product findBySkuCode(String skuCode);
}
