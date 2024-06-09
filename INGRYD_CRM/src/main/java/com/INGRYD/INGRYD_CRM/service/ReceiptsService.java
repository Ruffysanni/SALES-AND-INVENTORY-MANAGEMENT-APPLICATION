package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Payment;
import com.INGRYD.INGRYD_CRM.model.Receipt;
import com.INGRYD.INGRYD_CRM.repository.ReceiptsRepository;
import jakarta.mail.MessagingException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    private final MessageService messageService;

    public ReceiptsService(ReceiptsRepository receiptsRepository, PaymentService paymentService, MessageService messageService) {
        this.receiptsRepository = receiptsRepository;
        this.paymentService = paymentService;
        this.messageService = messageService;
    }

    //Get all Receipts
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return new ResponseEntity<>(receiptsRepository.findAll(), HttpStatus.OK);
    }

    // Get Receipts by ID
    public ResponseEntity<Receipt> getReceiptById(Long id) {
        Optional<Receipt> receipt = receiptsRepository.findById(id);
        if (receipt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(receipt.get(), HttpStatus.OK);
        }
    }

    //Create a new Receipt
    @Transactional
    @ConditionalOnProperty(value = "notification.role", havingValue = "CUSTOMER")
    public ResponseEntity<Receipt> createReceipt(String receiver,Payment payment) throws IOException, MessagingException {
        paymentService.createPayment(payment);
        Receipt receipt = new Receipt();
        createPDF(receipt);
        messageService.sendReceiptNotification(STR."This is a receipt notification for the good bought : Narration: \{receipt.getNarration()}Date: \{receipt.getReceiptDate()}Amount: \{receipt.getAmount()}");
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
