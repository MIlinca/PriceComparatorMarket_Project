package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {
}
