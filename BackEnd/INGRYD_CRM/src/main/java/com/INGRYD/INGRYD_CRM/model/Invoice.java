package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;



@Entity
@Table(name = "invoice_table", schema = "salesgryd")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "invoiceID")
    private long id;

    @NotEmpty
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate invoiceDate;

    @NotBlank(message = "amount due is mandatory")
    @NotNull
    @Positive(message = "Amount due must be positive")
    private double amountDue;

    @NotEmpty
    @NotBlank(message = "invoice due date is mandatory")
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesID")
    private Sale sales;

}
