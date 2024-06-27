package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Sale;
import com.INGRYD.INGRYD_CRM.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    //Get All Sales
    @GetMapping("/all")
    public  ResponseEntity<List<Sale>> getAllSales() {
        return saleService.getAllSales();
    }

    //Get a Sale By ID
    @GetMapping("/all/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id).getBody();
    }

    //Create a new Sale
    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    //Update an existing sale by id
    @PutMapping("/all/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale saleDetails) {
        return saleService.updateSale(id, saleDetails);
    }

    //Delete a sale by id
    @DeleteMapping("/all/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        return saleService.deleteSale(id);
    }

    //Get all sales within a specified date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Sale>> getSalesByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return saleService.getSaleByDateRange(startDate, endDate);
    }

    //Get all sales by SaleRepId within a specified date range
    @GetMapping("/salesRep/{salesRepId}/date-range")
    public ResponseEntity<List<Sale>> getSalesBySalesRepIdAndDateRange(
            @PathVariable Long salesRepId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return saleService.getSalesBySaleRepIdAndDateRange(salesRepId, startDate, endDate);
    }

}
