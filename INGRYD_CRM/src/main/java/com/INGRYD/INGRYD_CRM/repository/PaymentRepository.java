package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long>{

}
