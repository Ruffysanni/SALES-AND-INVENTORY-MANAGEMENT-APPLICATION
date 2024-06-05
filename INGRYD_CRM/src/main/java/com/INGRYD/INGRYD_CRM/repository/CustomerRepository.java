package com.INGRYD.INGRYD_CRM.repository;
import com.INGRYD.INGRYD_CRM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerName(String name);
}
