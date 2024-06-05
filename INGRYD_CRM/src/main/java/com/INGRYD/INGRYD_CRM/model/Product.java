package com.INGRYD.INGRYD_CRM.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity(name = "_products_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private Long category_id;

    @NotNull
    @NotBlank
    private String productName;

    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Double price;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;


}

