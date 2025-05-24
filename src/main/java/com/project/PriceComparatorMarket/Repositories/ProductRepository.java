package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
