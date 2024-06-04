package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //getCustomerAll
    public ResponseEntity<List<Customer>> getAllCustomer () {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    //getCustomerById
    public ResponseEntity<Customer> getCustomerById (Long id){
        return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
    }
    //getCustomerName
    public ResponseEntity<Customer> getCustomerName(String name){
        return new ResponseEntity<>(customerRepository.findByCustomerName(name), HttpStatus.OK);
    }

    //postCustomer
    public ResponseEntity<Customer> postCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);

        return  new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    //updateCustomer
    public ResponseEntity<Customer> updateCustomer(Long id, Customer replacementDetails){
        Customer customer = customerRepository.findById(id).get();
        customer.setName(replacementDetails.getName());
        customer.setEmail(replacementDetails.getEmail());
        customer.setPhoneNumber(replacementDetails.getPhoneNumber());
        customer.setAddress(replacementDetails.getAddress());

        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }


    //deleteCustomer
    public ResponseEntity<Customer> deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).get();
        customerRepository.deleteById(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
