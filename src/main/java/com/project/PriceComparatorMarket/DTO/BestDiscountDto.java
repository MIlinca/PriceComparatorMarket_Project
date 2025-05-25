package com.project.PriceComparatorMarket.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestDiscountDto {

    private String productName;
    private String store;
    private int percentageOfDiscount;
}
