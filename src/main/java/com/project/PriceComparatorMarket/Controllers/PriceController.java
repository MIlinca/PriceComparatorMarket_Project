package com.project.PriceComparatorMarket.Controllers;

import com.project.PriceComparatorMarket.DTO.PriceHistoryDto;
import com.project.PriceComparatorMarket.DTO.RecommendationDto;
import com.project.PriceComparatorMarket.Services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    final private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("priceHistory")
    public ResponseEntity<List<PriceHistoryDto>> getPriceHistory(@RequestParam String productName, @RequestParam(required = false) String store, @RequestParam(required = false) String category, @RequestParam(required = false) String brand)
    {
      List<PriceHistoryDto> prices=priceService.priceHistory(productName,store,category,brand);
      return ResponseEntity.ok(prices);
    }

    @GetMapping("/recommandation")
    public List<RecommendationDto> getBestValueByCategory(@RequestParam String category) {
        return priceService.getRecommendation(category);
    }
}

