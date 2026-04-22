package com.nitin.rating_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponse {

    private Long id;
    private Integer ratingValue;
    private String productSkuCode;
    private String description;
}
