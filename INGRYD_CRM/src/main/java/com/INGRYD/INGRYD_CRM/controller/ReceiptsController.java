package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.model.Receipts;
import com.INGRYD.INGRYD_CRM.service.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ingryd_crm/receipts")
public class ReceiptsController {
    @Autowired
    private ReceiptsService receiptsService;

    //Get all Receipts
    @GetMapping("")
    public ResponseEntity<List<Receipts>> getAllReceipts(){
        return receiptsService.getAllReceipts();
    }

    //Get Receipts by ID
    @GetMapping("/{id}")
    public ResponseEntity<Receipts> getReceiptById (@PathVariable Long id) {
        return receiptsService.getReceiptById(id);
    }

    //Create a new Receipt
    @PostMapping
    @Transactional
    public ResponseEntity<Receipts> createReceipt (@RequestBody Payment payment){
        return receiptsService.createReceipt(payment);
    }

    //Get Receipts by Date Range
    @GetMapping("/date-range")
    public ResponseEntity<List<Receipts>> getReceiptsByDateRange(@RequestParam LocalDate startDate, LocalDate endDate){
        return receiptsService.getReceiptsByDateRange(startDate,endDate);
    }


}