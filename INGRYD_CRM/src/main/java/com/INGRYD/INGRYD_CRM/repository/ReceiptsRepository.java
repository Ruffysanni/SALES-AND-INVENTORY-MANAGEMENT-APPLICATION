package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptsRepository extends JpaRepository<Receipts, Long> {
    Receipts findByReceiptId(Long id);
    List<Receipts> findByReceiptDateBetween(LocalDate startDate, LocalDate endDate);
}
