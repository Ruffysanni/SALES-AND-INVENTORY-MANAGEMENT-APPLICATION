package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.model.Receipt;
import com.INGRYD.INGRYD_CRM.service.ReceiptsService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptsController {
    private final ReceiptsService receiptsService;

    public ReceiptsController(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }

    //Get all Receipts
    @GetMapping("/all")
    public ResponseEntity<List<Receipt>> getAllReceipts(){
        return receiptsService.getAllReceipts();
    }

    //Get Receipts by ID
    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById (@PathVariable Long id) {
        return receiptsService.getReceiptById(id);
    }

    //Create a new Receipt
    @PostMapping
    @Transactional
    public ResponseEntity<Receipt> createReceipt (@RequestBody Payment payment) throws IOException, MessagingException {
        return receiptsService.createReceipt(payment);
    }

    //Get Receipts by Date Range
    @GetMapping("/date-range")
    public ResponseEntity<List<Receipt>> getReceiptsByDateRange(@RequestParam LocalDate startDate, LocalDate endDate){
        return receiptsService.getReceiptsByDateRange(startDate,endDate);
    }


}
