package com.project.PriceComparatorMarket.Services;

import com.project.PriceComparatorMarket.DTO.BasketDto;
import com.project.PriceComparatorMarket.DTO.BasketItem;
import com.project.PriceComparatorMarket.DTO.ShoppingListItemDto;
import com.project.PriceComparatorMarket.Models.Price;
import com.project.PriceComparatorMarket.Models.Product;
import com.project.PriceComparatorMarket.Repositories.PriceRepository;
import com.project.PriceComparatorMarket.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShoppingBasketService {
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    public ShoppingBasketService(ProductRepository productRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public List<ShoppingListItemDto> optimizeBasket(BasketDto basketDto){
        List<ShoppingListItemDto> shoppingList=new ArrayList<>();
        for(BasketItem item: basketDto.getItems()){
            List<Product> products = productRepository.findByName(item.getProductName());
            if (products.isEmpty()) throw new NoSuchElementException("No product found!");
            Product product = products.get(0);

            if(product==null)
                throw new NoSuchElementException("No product found!");

            List<Price> prices = priceRepository.findCheapestPrice(product.getId());
            Price cheapestPrice=prices.get(0);
            if(cheapestPrice==null)
                throw new NoSuchElementException("No price found!");

            String store=cheapestPrice.getStore().getName();

            ShoppingListItemDto shoppingListDto=new ShoppingListItemDto();
            shoppingListDto.setProductName(product.getName());
            shoppingListDto.setStore(store);
            shoppingListDto.setPrice(cheapestPrice.getPrice());

            shoppingList.add(shoppingListDto);
        }

        return shoppingList;
    }
}
