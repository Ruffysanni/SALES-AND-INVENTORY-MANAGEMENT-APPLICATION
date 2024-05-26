package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Customer findByCustomerName(String name);
}
