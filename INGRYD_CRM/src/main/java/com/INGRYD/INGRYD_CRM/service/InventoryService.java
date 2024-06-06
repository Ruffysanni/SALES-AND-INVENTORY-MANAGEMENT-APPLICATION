package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.exception.InsufficientStockException;
import com.INGRYD.INGRYD_CRM.model.Enum.Status;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.InventoryRepository;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Inventory>> getAllInventories() {
        return new ResponseEntity<>(inventoryRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Inventory> getInventoryById(long id) {
        return new ResponseEntity<>(inventoryRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    public ResponseEntity<List<Inventory>> getInventoryByProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow();
        return new ResponseEntity<>(inventoryRepository.findByProduct(product), HttpStatus.OK);
    }

    public ResponseEntity<Inventory> createInventory(int stockQuantity, Product product) {
        Inventory inventory = new Inventory();
        inventory.setStockQuantity(stockQuantity);
        inventory.setRemainingQuantity(stockQuantity);
        inventory.setStatus(Status.AVAILABLE_IN_STOCK);
        inventory.setProduct(product);
        return new ResponseEntity<>(inventoryRepository.save(inventory), HttpStatus.CREATED);
    }

    public ResponseEntity<Inventory> updateInventory(long id, Inventory updatedInventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow();
        existingInventory.setStockQuantity(updatedInventory.getStockQuantity());
        existingInventory.setRemainingQuantity(updatedInventory.getRemainingQuantity());
        existingInventory.setStatus(Status.AVAILABLE_IN_STOCK);
        return new ResponseEntity<>(inventoryRepository.save(existingInventory), HttpStatus.ACCEPTED);
    }

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
            }
            if (inventory.getRemainingQuantity() >= itemQuantity) {
                inventory.setRemainingQuantity((int) (inventory.getRemainingQuantity() - itemQuantity));

                if (inventory.getRemainingQuantity() == 0) {
                    inventory.setStatus(Status.OUT_OF_STOCK);
                }
                inventoryRepository.save(inventory);
            }
        }
        return ResponseEntity.ok("Successful");
    }

}