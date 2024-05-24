package com.INGRYD.INGRYD_CRM.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table
@Data
public class SalesRep {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "Sales_Representatives")
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
            message = "Invalid phone number format")
    private String phoneNumber;
    @NotNull
    @NotBlank(message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Size(min = 8, max = 13, message = "Password must contain a minimum of 8 and a maximum of 13 characters")
    @Pattern(regexp ="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,13}",
            message = "Password must contain at least one lowercase, one uppercase, one digit, one special character, and be between 8 and 13 characters long")
    private String password;

    public SalesRep(long id, String name, String username, String phoneNumber, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public SalesRep() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SalesRep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
