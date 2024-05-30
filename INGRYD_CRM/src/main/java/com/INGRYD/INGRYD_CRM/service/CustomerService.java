package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Customer;
import com.INGRYD.INGRYD_CRM.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    //updateCustomer

    //deleteCustomer

}
