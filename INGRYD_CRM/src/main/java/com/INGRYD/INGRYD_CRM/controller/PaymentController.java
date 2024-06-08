package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.service.PaymentService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Get all Payments
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get a Payment by ID
    @GetMapping("/all/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Create a new Payment
    @PostMapping
    public ResponseEntity<Payment> createPayment(String receiver,@RequestBody Payment payment) throws MessagingException {
        return paymentService.createPayment(receiver,payment);
    }

    // Update an existing Payment
    @PutMapping("/all/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails){
        Payment updatedPayment = paymentService.updatePayment(id, paymentDetails).getBody();
        if(updatedPayment != null){
            return ResponseEntity.ok(updatedPayment);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Payment
    @DeleteMapping("/all/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    // Get Payments by Date Range
    @GetMapping("/date-range")
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return paymentService.getPaymentsByDateRange(startDate, endDate);
    }

}