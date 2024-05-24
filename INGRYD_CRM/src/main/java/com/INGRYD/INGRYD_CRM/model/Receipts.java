package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;

@Entity(name = "receipts_table")
public class Receipts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private int id;

    private String receiptDate;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sales sales;

    public Receipts() {
    };

    public Receipts(String receiptDate, double amount, Sales sales) {
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public double getAmount() {
        return amount;
    }

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