package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.Service.InventoryService;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
// This gets all the inventories
    @GetMapping("/all_inventories")
    public ResponseEntity<List<Inventory>> searchAllInventories() {
        return inventoryService.getAllInventories();
    }
//    This gets the inventory by id
    @GetMapping("/inventory_by_id/{id}")
    public ResponseEntity<Inventory> searchInventoryById(@PathVariable long id) {
        return inventoryService.getInventoryById(id);
    }
//   This gets the inventory associated to each product
    @GetMapping("/inventory_by_product/{product}")
    public ResponseEntity<List<Inventory>> searchInventoryByProduct(@PathVariable Product product) {
        return inventoryService.getInventoryByProduct(product.getProduct_id());
    }
//    This creates the inventory for each product
    @PostMapping("/post_inventory")
    public ResponseEntity<Inventory> postNewInventory(@RequestBody @Valid int stockQuantity, Product product) {
        return inventoryService.createInventory(stockQuantity, product.getProduct_id());
    }
//    This updates the inventory record associated to each product
    @PutMapping("/update_inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable long id, @Valid @RequestBody Inventory updatedInventory) {
        return inventoryService.updateInventory(id, updatedInventory);
    }
//    This deletes the inventory record of each product
    @DeleteMapping("/delete_inventory/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable long id) {
        return inventoryService.deleteInventory(id);
    }
//    This tracks the inventory of the product being sold out
    @PostMapping("/inventory_tracking")
    public ResponseEntity<String> inventoryTracking(@Valid @RequestBody Product product, long quantity) {
        return inventoryService.inventoryTracking(product, quantity);
    }
}
