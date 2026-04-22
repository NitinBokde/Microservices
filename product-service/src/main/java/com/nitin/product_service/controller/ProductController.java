package com.nitin.product_service.controller;

import com.nitin.product_service.dto.ProductRequest;
import com.nitin.product_service.dto.ProductResponse;
import com.nitin.product_service.entity.Product;
import com.nitin.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping
    public String resetRating(@RequestParam String skuCode){
        productService.resetRating(skuCode);
        return "Rating Reset Successfully";
    }

//    @GetMapping("/{skuCode}")
//    @ResponseStatus(HttpStatus.OK)
//    public ProductResponse findProductBySkuCode(@PathVariable String skuCode){
//        return productService.findProductBySkuCode(skuCode);
//    }

}
