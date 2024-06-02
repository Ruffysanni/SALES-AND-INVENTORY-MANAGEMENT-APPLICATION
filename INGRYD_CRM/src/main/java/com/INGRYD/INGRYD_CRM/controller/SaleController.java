package com.INGRYD.INGRYD_CRM.controller;


import com.INGRYD.INGRYD_CRM.model.Sale;
import com.INGRYD.INGRYD_CRM.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    //Get All Sales
    @GetMapping
    public  List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    //Get a Sale By ID
    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id).getBody();
    }

    //Create a new Sale
    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    //Update an existing sale by id
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale saleDetails) {
        return saleService.updateSale(id, saleDetails);
    }

    //Delete a sale by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        return saleService.deleteSale(id);
    }

    //Get all sales within a specified date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Sale>> getSalesByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return saleService.getSaleByDateRange(startDate, endDate);
    }

    //Get all sales by SaleRepId within a specified date range
    @GetMapping("/salesRep/{saleRepId}/date-range")
    public ResponseEntity<List<Sale>> getSalesBySaleRepIdAndDateRange(
            @PathVariable String saleRepId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return saleService.getSalesBySaleRepIdAndDateRange(saleRepId, startDate, endDate);
    }

}
