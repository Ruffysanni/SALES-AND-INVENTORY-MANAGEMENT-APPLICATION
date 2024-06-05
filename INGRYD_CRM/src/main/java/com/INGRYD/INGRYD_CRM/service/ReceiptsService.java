package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.model.Receipt;
import com.INGRYD.INGRYD_CRM.repository.ReceiptsRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
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
    private final ReceiptsRepository receiptsRepository;

    private final PaymentService paymentService;

    public ReceiptsService(ReceiptsRepository receiptsRepository, PaymentService paymentService) {
        this.receiptsRepository = receiptsRepository;
        this.paymentService = paymentService;
    }

    //Get all Receipts
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return new ResponseEntity<>(receiptsRepository.findAll(), HttpStatus.OK);
    }

    //Get Receipts by ID
    public ResponseEntity<Receipt> getReceiptById(Long id) {
        Optional<Receipt> receipt = receiptsRepository.findById(id);
        if (receipt.isEmpty()) {
            return new ResponseEntity<>(receiptsRepository.findByReceiptId(id), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(receiptsRepository.findByReceiptId(id), HttpStatus.OK);
        }
    }

    //Create a new Receipt
    @Transactional
    public ResponseEntity<Receipt> createReceipt(Payment payment) throws IOException {
        paymentService.createPayment(payment);
        Receipt receipt = new Receipt();
        createPDF(receipt);

        Receipt savedReceipt = receiptsRepository.save(receipt);
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }

    private void createPDF(Receipt receipt) throws IOException{
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.valueOf("Helvetica-Bold")), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Receipt Details");
            contentStream.endText();

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.valueOf("Helvetica")), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 650);
            contentStream.showText(STR."Amount: \{receipt.getAmount()}");
            contentStream.newLine();
            contentStream.showText(STR."Narration: \{receipt.getNarration()}");
            contentStream.endText();
        }

        document.save("receipt.pdf");
        document.close();
    }

    //Get Receipts by Date Range
    public ResponseEntity<List<Receipt>> getReceiptsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Receipt> receipts = receiptsRepository.findByReceiptDateBetween(startDate, endDate);
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }



}
