package com.INGRYD.INGRYD_CRM.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Entity(name = "customers_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @NotEmpty
    @Null
    @Size(min = 2, max = 100, message = "Name must be more than 2, and less than 100 characters")
    private String name;

    @Email
    private String email;

    @NotEmpty
    @NotBlank(message = "phone number is mandatory")
    @NotNull
    @Pattern(regexp = "^\\\\+234(80|70|90|81)\\\\d{8}$|^(080|070|090|081)\\\\d{8}$",
            message = "Invalid phone number format, phone number must start with +2380, 23481,23470,23490 or 080,081,070,090")
    private String phoneNumber;

    @NotBlank
    @Null
    @Size(min = 3, max = 100, message = "Address must be more than 2, and less than 100 characters")
    private String address;
}
