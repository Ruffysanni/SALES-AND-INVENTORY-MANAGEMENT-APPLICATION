package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "sales_table")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String saleRepId;

    @OneToMany(mappedBy = "sale")
    private List<Payment> payments;

    public Sale() {
    }

    public Sale(Long id, String customerId, LocalDateTime saleDate, Double totalAmount, String saleRepId) {
        this.id = id;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.saleRepId = saleRepId;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(String saleRepId) {
        this.saleRepId = saleRepId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                ", saleRepId='" + saleRepId + '\'' +
                '}';
    }
}
