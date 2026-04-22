package com.nitin.product_service.service;

import com.nitin.product_service.dto.ProductRequest;
import com.nitin.product_service.dto.ProductResponse;
import com.nitin.product_service.entity.Product;
import com.nitin.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .skuCode((productRequest.getSkuCode()))
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(ProductService::mapToProductResponse).toList();
    }


    private static ProductResponse mapToProductResponse(Product p){
        return ProductResponse.builder()
                .id(p.getId())
                .description(p.getDescription())
                .price(p.getPrice())
                .name(p.getName())
                .skuCode(p.getSkuCode())
                .averageRating(p.getAverageRating())
                .build();
    }

    @Transactional
    public void updateAverageRating(String skuCode,Integer ratingValue, Integer oldRatingValue){
        Product product = productRepository.findBySkuCode(skuCode);
        double oldAvg = product.getAverageRating();
        int oldCount = product.getRatingCount();

        double newAvg;
        if(oldRatingValue == null){
            newAvg = ( (oldAvg * oldCount)+ratingValue )/(oldCount+1);
           product.setRatingCount(oldCount+1);
        }else{
            newAvg =( (oldAvg * oldCount) -oldRatingValue + ratingValue)/(oldCount);
        }
        product.setAverageRating(newAvg);
        productRepository.save(product);

    }
    public void resetRating(String skuCode){
        Product product = productRepository.findBySkuCode(skuCode);
        product.setAverageRating(0.0);
        product.setRatingCount(0);
        productRepository.save(product);
    }
}
