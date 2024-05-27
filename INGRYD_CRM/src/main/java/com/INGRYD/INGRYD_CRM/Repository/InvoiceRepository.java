package com.INGRYD.INGRYD_CRM.Repository;

import com.INGRYD.INGRYD_CRM.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByInvoiceDate(Date invoiceDate);

}
