package model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity(name = "_products_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private Long category_id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Double price;
    private Category category;

    public Product() {
    }

    public Product(Long product_id, Long category_id, String name, String description, Double price, Category category) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
