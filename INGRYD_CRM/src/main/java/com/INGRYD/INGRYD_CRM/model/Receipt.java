package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Entity(name = "receipts_table")

public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long id;

    @NotEmpty
    @NotBlank(message = "Receipt date is mandatory")
    @NotNull
    @Temporal(TemporalType.DATE)
    private String receiptDate;

    @NotEmpty
    @NotBlank(message = "amount is mandatory")
    @NotNull
    @Positive(message = "Amount due must be positive")
    private Double amount;

    private String narration;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sale;

    public Receipt(Long id, String receiptDate, Double amount, String narration, Sale sale) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.narration = narration;
        this.sale = sale;
    }

    public Receipt() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
