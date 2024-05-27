package com.INGRYD.INGRYD_CRM.Repository;

import com.INGRYD.INGRYD_CRM.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findInventoryByProduct_id(Long product_id);
}
