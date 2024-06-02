package com.INGRYD.INGRYD_CRM.service;


import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all Payments
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }

    // Get Payment by ID
    public ResponseEntity<Payment> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(payment.get());
        }
    }

    // Create a new Payment
    public ResponseEntity<Payment> createPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    // Update an existing Payment
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
