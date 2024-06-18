package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/dashboard")
    public String customerLandingPage() {
        return "customer_landing_page";
    }

    //Get all Customers
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    //getCustomerById
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    //getCustomerName
    @GetMapping("/getCustomerName")
    public ResponseEntity<Customer> getCustomerName(@RequestParam String name) {
        return customerService.getCustomerName(name);
    }

    //postCustomer
    @PostMapping
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {

        return customerService.postCustomer(customer);
    }

    //updateCustomer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, Customer replacementDetails) {

        return customerService.updateCustomer(id, replacementDetails);
    }

    //deleteCustomer
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
