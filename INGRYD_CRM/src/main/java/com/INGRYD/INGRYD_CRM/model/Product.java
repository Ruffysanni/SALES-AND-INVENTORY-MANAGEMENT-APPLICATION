package com.INGRYD.INGRYD_CRM.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@Table(name = "products_table", schema = "salesgryd")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinColumn (name = "categoryID")
    private Category category;


}

