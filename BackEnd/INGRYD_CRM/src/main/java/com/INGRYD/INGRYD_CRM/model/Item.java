package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "items_table", schema = "salesgryd")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private double quantity;

    @NotBlank
    @NotNull
    private Double unitPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesID")
    private Sale sales;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productID")
    private Product products;

}