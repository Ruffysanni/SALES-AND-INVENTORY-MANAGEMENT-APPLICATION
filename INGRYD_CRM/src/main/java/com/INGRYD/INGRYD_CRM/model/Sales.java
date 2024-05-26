package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "sales_table")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private LocalDate salesDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String salesRepId;

    @OneToMany(mappedBy = "sales")
    private List<Payment> payments;

    public Sales(Long id, String customerId, LocalDate salesDate, Double totalAmount, String salesRepId) {
        this.id = id;
        this.customerId = customerId;
        this.salesDate = salesDate;
        this.totalAmount = totalAmount;
        this.salesRepId = salesRepId;
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

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", salesDate=" + salesDate +
                ", totalAmount=" + totalAmount +
                ", salesRepId='" + salesRepId + '\'' +
                '}';
    }
}
