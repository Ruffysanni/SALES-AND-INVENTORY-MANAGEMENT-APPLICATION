package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_table", schema = "salesgryd")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private Long id;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Long saleId;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sale sales;

}