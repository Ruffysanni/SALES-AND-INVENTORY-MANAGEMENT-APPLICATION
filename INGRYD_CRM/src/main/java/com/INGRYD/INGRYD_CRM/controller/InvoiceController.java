package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Invoice;
import com.INGRYD.INGRYD_CRM.repository.InvoiceRepository;
import com.INGRYD.INGRYD_CRM.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@CrossOrigin
public class InvoiceController {
    // Autowire invoice Repository,and invoice Service
    final InvoiceService invoiceService;
    final InvoiceRepository invoiceRepository;
    // Create Constructor for invoice Repository,and invoice Service
    public InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;
    }
// Post mapping to create a new invoice
    @PostMapping("/create")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }
// Put mapping to edit the details of an invoice
    @PutMapping("/edit/{id}")
    public Invoice editInvoice(@RequestBody Invoice invoice,@PathVariable long id) {
        return invoiceService.updateInvoice(invoice,id);
    }
// Delete mapping to delete an invoice
    @DeleteMapping("/delete/{id}")
    public void deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
// Get mapping to fetch all the invoices
    @GetMapping("/getAll")
    public List<Invoice> getAllInvoices(){
        return invoiceService.getAllInvoices();
    }
// Get mapping to fetch details of an invoice
    @GetMapping("/details/{id}")
    public Invoice getInvoiceDetails(@PathVariable long id){
        return invoiceService.getInvoiceDetails(id);
    }
}
