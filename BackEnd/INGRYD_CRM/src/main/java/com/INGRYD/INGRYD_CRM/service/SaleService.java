package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Sale;
import com.INGRYD.INGRYD_CRM.model.SalesRep;
import com.INGRYD.INGRYD_CRM.repository.SaleRepository;
import com.INGRYD.INGRYD_CRM.repository.SalesRepRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final SalesRepRepository salesRepRepository;

    public SaleService(SaleRepository saleRepository, SalesRepRepository salesRepRepository) {
        this.saleRepository = saleRepository;
        this.salesRepRepository = salesRepRepository;
    }

    //Get All Sales
    public ResponseEntity<List<Sale>> getAllSales() {
        return new ResponseEntity<>(saleRepository.findAll(), HttpStatus.OK);
    }

    //Get a Sale By ID
    public ResponseEntity<Sale> getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); //if (sale.isEmpty()) {return ResponseEntity.notFound().build();} else {return ResponseEntity.ok(sale.get());}
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
            SalesRep salesRep = salesRepRepository.findById(saleDetails.getSalesRep().getId()).orElseThrow();
            saleToUpdate.setSalesRep(salesRep);
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
    public ResponseEntity<List<Sale>> getSaleByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = saleRepository.findBySaleDateBetween(startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    //Get all sales by SaleRepId within a specified date range
    public ResponseEntity<List<Sale>> getSalesBySaleRepIdAndDateRange(Long salesRepId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sale = saleRepository.findBySalesRepIdAndSaleDateBetween(salesRepId, startDate, endDate);
        return ResponseEntity.ok(sale);
    }
}

