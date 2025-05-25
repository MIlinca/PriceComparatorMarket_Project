package com.project.PriceComparatorMarket.Services;

import com.project.PriceComparatorMarket.DTO.BestDiscountDto;
import com.project.PriceComparatorMarket.DTO.DiscountDto;
import com.project.PriceComparatorMarket.Models.Discount;
import com.project.PriceComparatorMarket.Repositories.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }
    public List<BestDiscountDto> getBestDiscount(){
        LocalDate today=LocalDate.now();
        LocalDate fixedDate = LocalDate.of(2025, 5, 8);
        List<Discount> activeDiscount=discountRepository.findActiveDiscounts(fixedDate);
        List<BestDiscountDto> products=new ArrayList<>();
        for (Discount d : activeDiscount) {
            BestDiscountDto bestDiscountDto = new BestDiscountDto();
            bestDiscountDto.setProductName(d.getProduct().getName());
            bestDiscountDto.setStore(d.getStore().getName());
            bestDiscountDto.setPercentageOfDiscount(d.getPercentageOfDiscount());
            products.add(bestDiscountDto);
        }
        return products;
    }
    public List<DiscountDto> getNewDiscount() {
        //LocalDate now = LocalDate.now();
        LocalDate now = LocalDate.of(2025, 5, 7);
        LocalDate yesterday = now.minusDays(1);
        LocalDate nextDay = now.plusDays(1);
        List<Discount> newDiscounts = discountRepository.findNewDiscounts(yesterday, nextDay);

        List<DiscountDto> discountDtos = new ArrayList<>();
        for (Discount discount : newDiscounts) {
            DiscountDto discountDto = new DiscountDto();
            discountDto.setProduct(discount.getProduct().getName());
            discountDto.setStore(discount.getStore().getName());
            discountDto.setPercentageOfDiscount(discount.getPercentageOfDiscount());
            discountDto.setCreatedAt(discount.getFromDate());
            discountDtos.add(discountDto);
        }
        return discountDtos;
    }

}
