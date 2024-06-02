package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Sale;
import com.INGRYD.INGRYD_CRM.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    //Get All Sales
    public List<Sale> getAllSales() {
        List<Sale> sale = saleRepository.findAll();
        return ResponseEntity.ok(sale).getBody();
    }

    //Get a Sale By ID
    public ResponseEntity<Sale> getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sale.get());
        }
    }

    //Create a new Sale
    public ResponseEntity<Sale> createSale(Sale sale) {
        Sale savedSale = saleRepository.save(sale);
        return ResponseEntity.ok(savedSale);
    }

    //Update an existing sale by id
    public ResponseEntity<Sale> updateSale(Long id, Sale saleDetails) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            Sale saleToUpdate = sale.get();
            saleToUpdate.setSaleDate(saleDetails.getSaleDate());
            saleToUpdate.setTotalAmount(saleDetails.getTotalAmount());
            saleToUpdate.setSaleRepId(saleDetails.getSaleRepId());
            Sale updatedSale = saleRepository.save(saleToUpdate);
            return ResponseEntity.ok(updatedSale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete a sale by id
    public ResponseEntity<Void> deleteSale(Long id) {
        Optional<Sale> sales = saleRepository.findById(id);
        if (sales.isPresent()) {
            saleRepository.delete(sales.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Get all sales within a specified date range
    public ResponseEntity<List<Sale>> getSaleByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Sale> sales = saleRepository.findBySaleDateBetween(startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    //Get all sales by SaleRepId within a specified date range
    public ResponseEntity<List<Sale>> getSalesBySaleRepIdAndDateRange(String saleRepId, LocalDate startDate, LocalDate endDate) {
        List<Sale> sale = saleRepository.findBySaleRepIdAndSaleDateBetween(saleRepId, startDate, endDate);
        return ResponseEntity.ok(sale);
    }
}