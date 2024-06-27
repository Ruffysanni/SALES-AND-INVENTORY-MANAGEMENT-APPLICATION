package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "sales_table", schema = "salesgryd")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="salesID")
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

    @OneToMany(mappedBy = "sales")
    private List<Item> items;


    @OneToMany(mappedBy = "sales")
    private List<Payment> payments;

    @OneToMany(mappedBy = "sales")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "sales")
    private List<Receipt> receipts;
}
