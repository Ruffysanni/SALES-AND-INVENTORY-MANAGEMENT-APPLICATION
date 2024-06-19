package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Receipts;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptsRepository extends JpaRepository<Receipts, Long> {

    List<Receipts> findByReceiptDateBetween(@NotNull @NotEmpty LocalDate startDate, LocalDate endDate);
}
