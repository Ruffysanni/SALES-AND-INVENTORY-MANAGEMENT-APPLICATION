package com.INGRYD.INGRYD_CRM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity(name = "customers_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

//    @NotBlank
//    @Length(min = 3)
//    private String firstName;
//
//    @NotBlank
//    @Length(min = 3)
//    private String lastName;

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
//    @Pattern(regexp = "^\\\\+234(80|70|90|81)\\\\d{8}$|^(080|070|090|081)\\\\d{8}$",
//            message = "Invalid phone number format, phone number must start with +2380, 23481,23470,23490 or 080,081,070,090")
    private String phoneNumber;

    @NotBlank
    @Null
    @Size(min = 3, max = 100, message = "Address must be more than 2, and less than 100 characters")
    private String address;

    public Customer() {}

    public Customer(Long id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
