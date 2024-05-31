package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Invoice;
import com.INGRYD.INGRYD_CRM.repository.InvoiceRepository;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Resource
@Transactional
public class InvoiceService {
    final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Invoice invoice, Long id) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invoice not found"));
        existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
        existingInvoice.setAmountDue(invoice.getAmountDue());
        existingInvoice.setDueDate(invoice.getDueDate());
        return invoiceRepository.save(existingInvoice);
    }
    public Invoice getInvoiceDetails(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invoice not found"));
    }
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ServiceException("invoice not found");
        }
        invoiceRepository.deleteById(id);
    }
}
