package com.project.PriceComparatorMarket.Repositories;

import com.project.PriceComparatorMarket.Models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

    @Query("SELECT d FROM Discount d WHERE :today BETWEEN d.fromDate AND d.toDate ORDER BY d.percentageOfDiscount DESC")
    List<Discount> findActiveDiscounts(@Param("today") LocalDate today);

    @Query("SELECT d FROM Discount d WHERE d.fromDate >= :start AND d.fromDate < :end")
    List<Discount> findNewDiscounts(@Param("start") LocalDate start, @Param("end") LocalDate end);


}
