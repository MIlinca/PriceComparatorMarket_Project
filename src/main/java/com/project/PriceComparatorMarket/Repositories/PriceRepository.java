package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {
}
