package com.INGRYD.INGRYD_CRM.Service;

import com.INGRYD.INGRYD_CRM.exception.InsufficientStockException;
import com.INGRYD.INGRYD_CRM.model.Enum.Status;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.InventoryRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
//    This gets all the inventories
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return new ResponseEntity<>(inventoryRepository.findAll(), HttpStatus.OK);
    }
//   This gets inventory by id
    public ResponseEntity<Inventory> getInventoryById(long id) {
        return new ResponseEntity<>(inventoryRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }
// This gets the inventory by product
    public ResponseEntity<List<Inventory>> getInventoryByProduct(Product product) {
        return new ResponseEntity<>(inventoryRepository.findByProduct(product.getProduct_id()), HttpStatus.OK);
    }
//  This creates inventory for each product
    public ResponseEntity<Inventory> createInventory(int stockQuantity, Product product) {
        Inventory inventory = new Inventory();
        inventory.setStockQuantity(stockQuantity);
        inventory.setRemainingQuantity(stockQuantity);
        inventory.setStatus(Status.AVAILABLE_IN_STOCK);
        inventory.setProduct(product.getProduct_id());
        return new ResponseEntity<>(inventoryRepository.save(inventory), HttpStatus.CREATED);
    }
// This updates the inventory record of each product
    public ResponseEntity<Inventory> updateInventory(long id, Inventory updatedInventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow();
        existingInventory.setStockQuantity(updatedInventory.getStockQuantity());
        existingInventory.setRemainingQuantity(updatedInventory.getRemainingQuantity());
        existingInventory.setStatus(Status.AVAILABLE_IN_STOCK);
        return new ResponseEntity<>(inventoryRepository.save(existingInventory), HttpStatus.ACCEPTED);
    }
// This deletes the inventory records of each product
    public ResponseEntity<Inventory> deleteInventory(long id) {
        Inventory deletedInventory = inventoryRepository.findById(id).orElseThrow();
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>(deletedInventory, HttpStatus.ACCEPTED);
    }

    //  Logics on the Inventory Tracking
    public ResponseEntity<String> inventoryTracking(Product product, long itemQuantity) {
        List<Inventory> inventoryRecords = inventoryRepository.findByProduct(product);
        for (Inventory inventory : inventoryRecords) {
            if (itemQuantity > inventory.getRemainingQuantity()) {
                int quantityLeft = inventory.getRemainingQuantity();
                throw new InsufficientStockException(STR."Not enough stock available. Only\{quantityLeft} quantities left.");
            } else if (inventory.getRemainingQuantity() >= itemQuantity) {
                inventory.setRemainingQuantity((int) (inventory.getRemainingQuantity() - itemQuantity));
            } else if (inventory.getRemainingQuantity() <= 10) {
                inventory.setStatus(Status.LOW_STOCK);
            }
            else if (inventory.getRemainingQuantity() <= 5) {
                inventory.setStatus(Status.VERY_LOW_STOCK);
            } else if (inventory.getRemainingQuantity() == 0) {
                inventory.setStatus(Status.OUT_OF_STOCK);
            }
            inventoryRepository.save(inventory);
        }
        return ResponseEntity.ok("Successful");
    }
}