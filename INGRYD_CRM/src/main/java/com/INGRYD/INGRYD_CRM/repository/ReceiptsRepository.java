package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Receipts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptsRepository extends JpaRepository<Receipts, Long> {
  Receipts findByReceiptId(Long id);
}
