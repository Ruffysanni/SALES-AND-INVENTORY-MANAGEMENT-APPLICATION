package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;



@Data
@Entity
@Table(name = "customers_table", schema = "salesgryd")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100, message = "Name must be more than 2, and less than 100 characters")
    private String name;

    @NotNull
    @NotBlank(message = "username is mandatory")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    @Email
    @Column (name = "email")
    private String username;

    @NotNull
    @NotBlank(message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Size(min = 8, max = 13, message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Pattern(regexp ="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,13}",
            message = "Password must contain at least one lowercase, one uppercase, one digit, one special character, and be between 8 and 13 characters long")
    private String password;

    @NotEmpty
    @NotNull
    @NotBlank(message = "phone number is mandatory")
    @Pattern(regexp = "^\\+234(80|70|90|81)\\d{8}$|^(080|070|090|081)\\d{8}$",
            message = "Invalid phone number format, phone number must start with +23480,23481,23470,23490 or 080,081,070,090")
    private String phoneNumber;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100, message = "Address must be more than 2, and less than 100 characters")
    private String address;

    private Role roles;
}
