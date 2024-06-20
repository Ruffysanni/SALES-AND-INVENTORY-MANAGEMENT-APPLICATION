package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "receipts_table")
public class Receipts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Long id;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate receiptDate;


    @Column(nullable = false)
    private Double amount;

    private String narration;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sale sales;

    public Receipts() {
    }


    public Receipts(Long id, LocalDate receiptDate, Double amount, String narration, Sale sales) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.narration = narration;
        this.sales = sales;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNarration() {
        return narration;
    }

    public Sale getSales() {
        return sales;

        }

        @Override
        public String toString () {
            return "Receipts{" +
                    "receiptDate='" + receiptDate + '\'' +
                    ", amount=" + amount +
                    ", sales=" + sales +
                    '}';
        }
    }
