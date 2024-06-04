package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Receipts;
import com.INGRYD.INGRYD_CRM.repository.ReceiptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptsService {
    /*
     * CRUD
     *
     * */
    @Autowired
    private ReceiptsRepository receiptsRepository;

    @Autowired
    private PaymentService paymentService;

    //Get all Receipts
    public ResponseEntity<List<Receipts>> getAllReceipts() {
        return new ResponseEntity<>(receiptsRepository.findAll(), HttpStatus.OK);
    }

    //Get Receipts by ID
    public ResponseEntity<Receipts> getReceiptById(Long id) {
        Optional<Receipts> receipt = receiptsRepository.findById(id);
        if (receipt.isEmpty()) {
            return new ResponseEntity<>(receiptsRepository.findByReceiptId(id), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(receiptsRepository.findByReceiptId(id), HttpStatus.OK);
        }
    }

    //Create a new Receipt
    @Transactional
    public ResponseEntity<Receipts> createReceipt(Payment payment) {
        paymentService.createPayment(payment);
        Receipts receipts = new Receipts();

        Receipts savedReceipt = receiptsRepository.save(receipts);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }


    //Get Receipts by Date Range
    public ResponseEntity<List<Receipts>> getReceiptsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Receipts> receipts = receiptsRepository.findByReceiptDateBetween(startDate, endDate);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }


}
