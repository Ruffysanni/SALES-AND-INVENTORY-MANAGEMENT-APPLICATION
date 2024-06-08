package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import com.INGRYD.INGRYD_CRM.service.InventoryService;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final ProductRepository productRepository;


    public InventoryController(InventoryService inventoryService, ProductRepository productRepository) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
    }

    @GetMapping("/all_inventories")
    public ResponseEntity<List<Inventory>> searchAllInventories() {
        return inventoryService.getAllInventories();
    }
    @GetMapping("/inventory_by_id/{id}")
    public ResponseEntity<Inventory> searchInventoryById(@PathVariable long id) {
        return inventoryService.getInventoryById(id);
    }
    @GetMapping("/inventory_by_product/{product}")
    public ResponseEntity<List<Inventory>> searchInventoryByProduct(@PathVariable Product product) {
        return inventoryService.getInventoryByProduct(product.getId());
    }
    @PostMapping("/post_inventory")
    public ResponseEntity<Inventory> postNewInventory(@RequestBody int stockQuantity, @RequestBody Long product_id) {
        Product product = productRepository.findById(product_id).orElseThrow();
        return inventoryService.createInventory(stockQuantity, product);
    }
    @PutMapping("/update_inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(String receiver,@PathVariable long id, @RequestBody Inventory updatedInventory) throws MessagingException {
        return inventoryService.updateInventory(receiver, id, updatedInventory);
    }
    @DeleteMapping("/delete_inventory/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable long id) {
        return inventoryService.deleteInventory(id);
    }
    @PostMapping("/inventory_tracking")
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP")
    public ResponseEntity<String> inventoryTracking(String receiver,@RequestBody Product product, long quantity) throws MessagingException {
        return inventoryService.inventoryTracking(receiver,product, quantity);
    }
}
