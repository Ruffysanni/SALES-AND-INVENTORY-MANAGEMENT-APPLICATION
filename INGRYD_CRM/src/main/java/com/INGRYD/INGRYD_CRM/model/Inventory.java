package com.INGRYD.INGRYD_CRM.model;

import com.INGRYD.INGRYD_CRM.model.Enum.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity(name = "inventory_table")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull
    @NotBlank(message = "Please enter a value")
    private int stockQuantity;
    @NotNull
    @NotBlank()
    private int remainingQuantity;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Inventory(Long id, Product product, int stockQuantity, int remainingQuantity, Status status) {
        this.id = id;
        this.product = product;
        this.stockQuantity = stockQuantity;
        this.remainingQuantity = remainingQuantity;
        this.status = status;
    }

    public Inventory() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
