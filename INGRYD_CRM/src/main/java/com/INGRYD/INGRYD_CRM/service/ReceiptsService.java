package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.model.Receipts;
import com.INGRYD.INGRYD_CRM.repository.ReceiptsRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReceiptsService {
    /*
     * CRUD
     *
     * */
    @Autowired
    private ReceiptsRepository receiptsRepository;

    @Autowired
    private PaymentService paymentService;

    //Get all Receipts
    public ResponseEntity<List<Receipts>> getAllReceipts() {
        return new ResponseEntity<>(receiptsRepository.findAll(), HttpStatus.OK);
    }

    //Get Receipts by ID
    public ResponseEntity<Optional<Receipts>> getReceiptById(Long id) {
        Optional<Receipts> receipts = receiptsRepository.findById(id);
        if (receipts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(receipts, HttpStatus.OK);
        }
    }

    //Create a new Receipt
    @Transactional
    public ResponseEntity<Receipts> createReceipt(Receipts receipts,Payment payment) throws IOException{
        paymentService.createPayment(payment);

        createPDF(receipts);

        Receipts savedReceipt = receiptsRepository.save(receipts);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }

    private void createPDF(Receipts receipts) throws IOException{
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Receipt Details");
            contentStream.endText();

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 650);
            contentStream.showText("Amount: " + receipts.getAmount());
            contentStream.newLine();
            contentStream.showText("Narration: " + receipts.getNarration());
            contentStream.endText();
        }

        document.save("receipt.pdf");
        document.close();
    }

    //Get Receipts by Date Range
    public ResponseEntity<List<Receipts>> getReceiptsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Receipts> receipts = receiptsRepository.findByReceiptDateBetween(startDate, endDate);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }



}
