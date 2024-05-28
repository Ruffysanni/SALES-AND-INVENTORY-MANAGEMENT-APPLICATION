package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository <Sales, Long> {

    //This query method find all sales by salesDate
    List<Sales> findBySalesDate(LocalDate salesDate);

    //This query method find all sales by salesRepId
    List<Sales> findBySalesRepId(String salesRepId);

    //This query method find all sales by salesId and salesRepId
    List<Sales> findBySalesDateAndSalesRepId(LocalDate salesDate, String saleRepId);
}