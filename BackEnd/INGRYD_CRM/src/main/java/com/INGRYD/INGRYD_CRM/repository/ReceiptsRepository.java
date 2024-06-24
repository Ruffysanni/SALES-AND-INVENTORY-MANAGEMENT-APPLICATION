package com.INGRYD.INGRYD_CRM.repository;
import com.INGRYD.INGRYD_CRM.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface ReceiptsRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByReceiptDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<Receipt> findById(Long id);
}
