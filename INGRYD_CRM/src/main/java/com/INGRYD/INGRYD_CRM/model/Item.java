package com.INGRYD.INGRYD_CRM.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Entity(name = "items_table")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private Double quantity;

    @NotBlank
    @NotNull
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sale sales;


    @OneToOne
    @JoinColumn(name = "productID")
    private Product products;

}