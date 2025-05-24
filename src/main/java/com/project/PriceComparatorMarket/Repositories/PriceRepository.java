package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {

    @Query(value = "SELECT * FROM price WHERE product_id = :productId ORDER BY price ASC LIMIT 1", nativeQuery = true)
    Price findCheapestPrice(@Param("productId")String product_id);
}
