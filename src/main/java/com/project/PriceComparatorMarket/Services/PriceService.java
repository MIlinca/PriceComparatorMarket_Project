package com.project.PriceComparatorMarket.Services;

import com.project.PriceComparatorMarket.DTO.PriceHistoryDto;
import com.project.PriceComparatorMarket.DTO.RecommendationDto;
import com.project.PriceComparatorMarket.Models.Price;
import com.project.PriceComparatorMarket.Repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {
    final private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceHistoryDto> priceHistory(String productName, String store, String category, String brand){
        List<Price> priceList=priceRepository.findPriceHistory(productName,store,category,brand);
        List<PriceHistoryDto> priceHistoryDto =new ArrayList<>();

        for(Price p:priceList){
            PriceHistoryDto phDt0=new PriceHistoryDto();
            phDt0.setDate(p.getDate());
            phDt0.setPrice(p.getPrice());
            phDt0.setStore(p.getStore().getName());
            priceHistoryDto.add(phDt0);
        }

        return priceHistoryDto;
    }
    public double calculatePriceUnit(Price price){
        double quantity=price.getProduct().getPackageQuantity();
        double productPrice=price.getPrice();
        return productPrice/quantity;
    }

    public List<RecommendationDto> getRecommendation(String category){
        List<Price> prices =priceRepository.findByProductCategory(category);
        List<RecommendationDto> recommendations = new ArrayList<>();

        for (Price p: prices){
            double unitPrice=calculatePriceUnit(p);
            RecommendationDto recommendationDto=new RecommendationDto();
            recommendationDto.setProductName(p.getProduct().getName());
            recommendationDto.setBrand(p.getProduct().getBrand());
            recommendationDto.setStore(p.getStore().getName());
            recommendationDto.setPriceUnit(unitPrice);
            recommendationDto.setUnit(p.getProduct().getPackageUnit());

            recommendations.add(recommendationDto);
        }
        recommendations.sort(new Comparator<RecommendationDto>() {
            @Override
            public int compare(RecommendationDto o1, RecommendationDto o2) {
                return Double.compare(o1.getPriceUnit(), o2.getPriceUnit());
            }
        });
        return recommendations;
    }
}
