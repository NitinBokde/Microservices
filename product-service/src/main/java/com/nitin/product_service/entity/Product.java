package com.nitin.product_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(value = "product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String skuCode;

    private String description;
    private BigDecimal price;


    @Builder.Default
    private int ratingCount = 0;

    @Builder.Default
    private double averageRating = 0.0;
}
