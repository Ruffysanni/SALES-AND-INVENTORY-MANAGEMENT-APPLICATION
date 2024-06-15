package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.PaymentRepository;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MessageService messageService;
    static Product product;
    public PaymentService(PaymentRepository paymentRepository, MessageService messageService) {
        this.paymentRepository = paymentRepository;
        this.messageService = messageService;
    }
    // Get all Payments
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }

    // Get Payment by ID
    public ResponseEntity<Payment> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());// this is the same as saying if(payment.isEmpty()) {return ResponseEntity.notFound().build();
    }

    // Create a new Payment
    @ConditionalOnProperty(value = "notification.role", havingValue = "SALES_REP")
    public ResponseEntity<Payment> createPayment(Payment payment) throws MessagingException {
        Payment savedPayment = paymentRepository.save(payment);
        messageService.sendPaymentNotification(STR."This is to notify the confirmation of the payment for Product Name: \{product.getProductName()}Product Category: \{product.getCategory()}\nProduct Description: \{product.getDescription()}\nProduct Price: \{product.getPrice()}\nPayment Method: \{payment.getPaymentMethod()}\nPayment Date: \{payment.getPaymentDate()}\nAmount Paid: \{payment.getAmount()}");
        return ResponseEntity.ok(savedPayment);
    }

    public ResponseEntity<Payment> updatePayment(Long id, Payment paymentDetails) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            Payment paymentToUpdate = payment.get();
            paymentToUpdate.setPaymentDate(paymentDetails.getPaymentDate());
            paymentToUpdate.setAmount(paymentDetails.getAmount());
            paymentToUpdate.setPaymentMethod(paymentDetails.getPaymentMethod());
            paymentToUpdate.setSaleId(paymentDetails.getSaleId());
            Payment updatedPayment = paymentRepository.save(paymentToUpdate);
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Payment
    public void deletePayment(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
            ResponseEntity.ok().build();
        } else {
            ResponseEntity.notFound().build();
        }
    }

    // Get Payments by Date Range
    public ResponseEntity<List<Payment>> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startDate, endDate);
        return ResponseEntity.ok(payments);
    }
}
