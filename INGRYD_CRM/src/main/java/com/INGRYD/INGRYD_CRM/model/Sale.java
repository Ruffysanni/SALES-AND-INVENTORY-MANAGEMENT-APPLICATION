package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity(name = "sales_table")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @Column(nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "salesRepID")
    private SalesRep salesRep;

    @OneToMany(mappedBy = "sale")
    private List<Item> items;


    @OneToMany(mappedBy = "sale")
    private List<Payment> payments;

    @OneToMany(mappedBy = "sale")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "sale")
    private List<Receipt> receipts;


}
