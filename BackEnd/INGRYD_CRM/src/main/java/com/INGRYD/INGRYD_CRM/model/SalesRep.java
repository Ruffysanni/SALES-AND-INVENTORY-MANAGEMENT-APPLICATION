package com.INGRYD.INGRYD_CRM.model;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "SalesRep_table", schema = "salesgryd")
@Data
public class SalesRep {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "SalesRepID")
    private long id;
    @NotEmpty
    @NotBlank(message = "Name is mandatory")
    @NotNull
    @Size(min = 2, max = 100, message = "Name must be more than 2,and less than 100 characters")
    private String name;
    @NotNull
    @NotBlank(message = "username is mandatory")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    @Email
    private String username;
    @NotEmpty
    @NotNull
    @NotBlank(message = "phone number is mandatory")
    @Pattern(regexp = "^\\+234(80|70|90|81)\\d{8}$|^(080|070|090|081)\\d{8}$",
            message = "Invalid phone number format, phone number must start with +23480,23481,23470,23490 or 080,081,070,090")
    private String phoneNumber;
    @NotNull
    @NotBlank(message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Size(min = 8, max = 13, message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Pattern(regexp ="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,13}",
            message = "Password must contain at least one lowercase, one uppercase, one digit, one special character, and be between 8 and 13 characters long")
    private String password;

    @OneToMany(mappedBy = "salesRep",cascade = CascadeType.ALL)
    private List<Sale> sales;

    @Setter
    @Getter
    private Role role;

}
