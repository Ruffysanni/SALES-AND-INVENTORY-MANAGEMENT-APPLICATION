package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity(name = "receipts_table")
public class Receipts {
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

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sale;

    public Receipts() {
    }

    ;

    public Receipts(Long id, String receiptDate, Double amount, Sale sale) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public Double getAmount() {
        return amount;
    }

    public Sale getSales() {
        return sale;
    }

    @Override
    public String toString() {
        return "Receipts{" +
                "receiptDate='" + receiptDate + '\'' +
                ", amount=" + amount +
                ", sale=" + sale +
                '}';
    }
}
