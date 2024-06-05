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

    private String narration;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sales sales;

    public Receipts() {
    }

    ;

    public Receipts(Long id, String receiptDate, Double amount, String narration, Sales sales) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.narration = narration;
        this.sales = sales;
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

    public String getNarration(){return narration;}

    public Sales getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Receipts{" +
                "receiptDate='" + receiptDate + '\'' +
                ", amount=" + amount +
                ", sales=" + sales +
                '}';
    }
}
