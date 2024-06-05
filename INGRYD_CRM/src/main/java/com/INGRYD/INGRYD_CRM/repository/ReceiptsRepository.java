package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptsRepository extends JpaRepository<Receipt, Long> {
    Receipt findByReceiptId(Long id);
    List<Receipt> findByReceiptDateBetween(LocalDate startDate, LocalDate endDate);
}
