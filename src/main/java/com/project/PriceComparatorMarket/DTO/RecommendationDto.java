package com.project.PriceComparatorMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDto {
    private String productName;
    private String brand;
    private String store;
    private double priceUnit;
    private String unit;

}
