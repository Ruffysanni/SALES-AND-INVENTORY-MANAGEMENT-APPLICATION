package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository <Sale, Long> {

    //This query method find all sales within a specify date range
    List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);

    //This query method find all sales by salesId and salesRepId within a specified date range
    List<Sale> findBySaleRepIdAndSaleDateBetween(String saleRepId, LocalDate startDate, LocalDate endDate);

}

