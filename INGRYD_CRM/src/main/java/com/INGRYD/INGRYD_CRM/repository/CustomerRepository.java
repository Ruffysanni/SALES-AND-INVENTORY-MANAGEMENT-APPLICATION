package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Customer;

public interface CustomerRepository {
    Customer findByCustomerName(String name);
}
