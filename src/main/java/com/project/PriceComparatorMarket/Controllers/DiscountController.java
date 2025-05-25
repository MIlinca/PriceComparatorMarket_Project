package com.project.PriceComparatorMarket.Controllers;


import com.project.PriceComparatorMarket.DTO.BestDiscountDto;
import com.project.PriceComparatorMarket.DTO.DiscountDto;
import com.project.PriceComparatorMarket.DTO.ShoppingListItemDto;
import com.project.PriceComparatorMarket.Services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @GetMapping("bestDiscount")
    public List<BestDiscountDto> bestDiscount(){
        return discountService.getBestDiscount();
    }

    @GetMapping("newDiscount")
    public List<DiscountDto> newDiscount(){
        return discountService.getNewDiscount();
    }
}
