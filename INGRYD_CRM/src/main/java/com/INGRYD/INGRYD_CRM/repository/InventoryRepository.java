package com.INGRYD.INGRYD_CRM.repository;

import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProduct(Product product);
}
