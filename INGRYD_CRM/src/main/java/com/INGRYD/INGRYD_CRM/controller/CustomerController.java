package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //Get all Customers
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomer () {
        return  customerService.getAllCustomer();
    }

    //getCustomerById
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById (@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    //getCustomerName
    @GetMapping("/getCustomerName")
    public ResponseEntity<Customer> getCustomerName(@RequestParam String name){
        return customerService.getCustomerName(name);
    }


    //updateCustomer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, Customer replacementDetails){

        return customerService.updateCustomer(id,replacementDetails);
    }

    //deleteCustomer
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }
}
