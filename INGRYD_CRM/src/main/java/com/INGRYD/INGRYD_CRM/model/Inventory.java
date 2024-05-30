package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "inventory_table")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;
    private Long product_id;
    @NotNull
    @NotBlank(message = "Please enter a value")
    private int stockQuantity;

    public Inventory() {}

    public Inventory(Long id, Long product_id, int stockQuantity) {
        this.id = id;
        this.product_id = product_id;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
