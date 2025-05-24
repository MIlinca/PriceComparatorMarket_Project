package com.project.PriceComparatorMarket.Controllers;

import com.project.PriceComparatorMarket.DTO.BasketDto;
import com.project.PriceComparatorMarket.DTO.ShoppingListItemDto;
import com.project.PriceComparatorMarket.Services.ShoppingBasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class ShoppingBasketController {
    private final ShoppingBasketService shoppingBasketService;


    public ShoppingBasketController(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @PostMapping("dailyMonitoring")
    public ResponseEntity<List<ShoppingListItemDto>> optimizeBasket(@RequestBody BasketDto basketDto){
        List<ShoppingListItemDto> optimizedList = shoppingBasketService.optimizeBasket(basketDto);
        return ResponseEntity.ok(optimizedList);
    }
}
