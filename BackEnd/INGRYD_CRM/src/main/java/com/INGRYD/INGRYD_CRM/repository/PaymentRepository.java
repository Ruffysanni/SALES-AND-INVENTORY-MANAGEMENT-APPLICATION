package com.INGRYD.INGRYD_CRM.repository;
import com.INGRYD.INGRYD_CRM.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long>{
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}
