package com.INGRYD.INGRYD_CRM.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Sales_Id")
    private long id;

    private LocalDate date;

    @NotEmpty
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate invoiceDate;

    @NotBlank(message = "amount due is mandatory")
    @NotNull
    @Positive(message = "Amount due must be positive")
    private double amountDue;

    @NotEmpty
    @NotBlank(message = "invoice due date is mandatory")
    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    // create constructors

    public Invoice(long id,LocalDate date, LocalDate invoiceDate, double amountDue, LocalDate dueDate) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
        this.date = invoiceDate;
    }

    public Invoice() {

    }


    // create getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate invoiceDate) {
        this.date = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + invoiceDate +
                ", invoiceDate=" + invoiceDate +
                ", amountDue=" + amountDue +
                ", dueDate=" + dueDate +
                '}';
    }
}
