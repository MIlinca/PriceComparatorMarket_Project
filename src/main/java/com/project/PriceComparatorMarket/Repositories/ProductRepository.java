package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findByName(String name);
}
