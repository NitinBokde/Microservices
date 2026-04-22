package com.nitin.rating_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatingRequest {
    private Long id;
    private Integer ratingValue;
    private String productSkuCode;
    private String description;
}
