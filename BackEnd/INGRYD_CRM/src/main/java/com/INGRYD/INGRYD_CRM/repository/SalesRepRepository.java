package com.INGRYD.INGRYD_CRM.repository;
import com.INGRYD.INGRYD_CRM.model.Enum.Role;
import com.INGRYD.INGRYD_CRM.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepRepository extends CrudRepository<SalesRep,Long>, JpaRepository<SalesRep, Long>{
    //this query finds Sales Representative by their Username ;
    Optional<SalesRep> findByUsername(String username);

    //this query searches for Sales Representative by their names
    SalesRep findByName(String name);
    //identify the roles of Sales Representatives
    List<SalesRep>findSalesRepByRole(Role role);
}
