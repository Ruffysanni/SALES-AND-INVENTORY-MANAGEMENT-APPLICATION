package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.model.Invoice;
import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.repository.InvoiceRepository;
import jakarta.mail.MessagingException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.hibernate.service.spi.ServiceException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class InvoiceService {
    final InvoiceRepository invoiceRepository;
    final PaymentService paymentService;
    final MessageService messageService;

    public InvoiceService(InvoiceRepository invoiceRepository, PaymentService paymentService, MessageService messageService) {
        this.invoiceRepository = invoiceRepository;
        this.paymentService = paymentService;
        this.messageService = messageService;
    }

    @Transactional
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP,CUSTOMER")
    public Invoice createInvoice(Payment payment, Customer customer) throws IOException, MessagingException {
        paymentService.createPayment(payment, String.valueOf(customer));

        Invoice invoice = new Invoice();
        createPDF(invoice);
        messageService.sendInvoiceNotification(
                String.format("This is an invoice notification for the goods bought: Due Date: %s Date: %s Amount: %s",
                        invoice.getDueDate(), invoice.getInvoiceDate(), invoice.getAmountDue()), String.valueOf(customer));
        return invoiceRepository.save(invoice);
    }
    private void createPDF(Invoice invoice) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Invoice Details");
            contentStream.endText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 650);
            contentStream.showText(String.format("Amount: %s", invoice.getAmountDue()));
            contentStream.newLine();
            contentStream.showText(String.format("Due Date: %s", invoice.getDueDate()));
            contentStream.endText();
        }
        document.save("invoice.pdf");
        document.close();
    }


    public Invoice updateInvoice(Invoice invoice, Long id) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invoice not found"));
        existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
        existingInvoice.setAmountDue(invoice.getAmountDue());
        existingInvoice.setDueDate(invoice.getDueDate());
        return invoiceRepository.save(existingInvoice);
    }
    public Invoice getInvoiceDetailsById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Invoice not found"));
    }
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    public List<Invoice> findInvoicesByDateRange(LocalDate startDate,LocalDate endDate) {
        if(startDate == null || endDate == null) {
            throw new ServiceException("Date range cannot be empty");
        }
        if(startDate.isAfter(endDate)) {
            throw new ServiceException("Start date cannot be after end date");
        }
        return invoiceRepository.findByInvoiceDateBetween(startDate,endDate);
    }
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ServiceException("invoice not found");
        }
        invoiceRepository.deleteById(id);
    }
}
