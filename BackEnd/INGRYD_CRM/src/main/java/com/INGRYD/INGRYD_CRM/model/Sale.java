package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "salesRepID")

    private SalesRep salesRep;

    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("sales")
    private List<Item> items;


    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)

    private List<Payment> payments;

    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)

    private List<Invoice> invoices;

    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)

    private List<Receipt> receipts;
}
