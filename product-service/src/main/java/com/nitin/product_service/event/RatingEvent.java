package com.nitin.product_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingEvent {
    private Integer oldRatingValue;
    private Integer ratingValue;
    private String productSkuCode;
}
