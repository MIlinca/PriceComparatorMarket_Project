package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {

    @Query(value = "SELECT * FROM price WHERE product_id = :productId ORDER BY price ASC LIMIT 1", nativeQuery = true)
    Price findCheapestPrice(@Param("productId") String product_id);

    @Query("SELECT p FROM Price p " +
            "WHERE LOWER(TRIM(p.product.name)) = LOWER(TRIM(:productName)) " +
            "AND (:store IS NULL OR p.store.name = :store) " +
            "AND (:category IS NULL OR p.product.category = :category) " +
            "AND (:brand IS NULL OR p.product.brand = :brand) " +
            "ORDER BY p.date ASC")
    List<Price> findPriceHistory(@Param("productName") String product_name,
                                 @Param("store") String store,
                                 @Param("category") String category,
                                 @Param("brand") String brand);

    @Query("SELECT p FROM Price p WHERE p.product.category = :category")
    List<Price> findByProductCategory(String category);
}

