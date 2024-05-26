package com.INGRYD.INGRYD_CRM.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Sales_Invoice")
    private long id;

    @NotEmpty
    @NotBlank(message = "invoice date  is mandatory")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;
    @NotEmpty
    @NotBlank(message = "amount due is mandatory")
    @NotNull
    @Positive(message = "Amount due must be positive")
    private double amountDue;

    @NotEmpty
    @NotBlank(message = "invoice due date is mandatory")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    // create constructors

    public Invoice(long id, Date invoiceDate, double amountDue, Date dueDate) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", amountDue=" + amountDue +
                ", dueDate=" + dueDate +
                '}';
    }
}
