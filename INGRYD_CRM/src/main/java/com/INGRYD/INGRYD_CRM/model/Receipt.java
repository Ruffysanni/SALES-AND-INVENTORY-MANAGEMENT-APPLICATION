package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "receipts_table", schema = "salesgryd")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiptID")
    private Long id;

    @NotEmpty
    @NotBlank(message = "Receipt date is mandatory")
    @NotNull
    private LocalDate receiptDate;

    @NotEmpty
    @NotBlank(message = "amount is mandatory")
    @NotNull
    @Positive(message = "Amount due must be positive")
    private Double amount;

    private String narration;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sale sales;

}
