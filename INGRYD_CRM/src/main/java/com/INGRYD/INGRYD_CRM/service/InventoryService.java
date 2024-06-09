package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Enum.Status;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.InventoryRepository;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    private final MessageService messageService;
    private final Product product;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, MessageService messageService, Product product) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.messageService = messageService;
        this.product = product;
    }

    //      This gets all the inventories

    public ResponseEntity<List<Inventory>> getAllInventories() {
        return new ResponseEntity<>(inventoryRepository.findAll(), HttpStatus.OK);
    }
//      This gets each inventory by its ID
    public ResponseEntity<Inventory> getInventoryById(long id) {
        return new ResponseEntity<>(inventoryRepository.findById(id).orElseThrow(), HttpStatus.OK);
   }
//      This gets each inventory by the product
    public ResponseEntity<List<Inventory>> getInventoryByProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow();
        return new ResponseEntity<>(inventoryRepository.findByProduct(product), HttpStatus.OK);
    }
//      This creates a new Inventory for each product
    public ResponseEntity<Inventory> createInventory(int stockQuantity, Product product) {
        Inventory inventory = new Inventory();
        inventory.setStockQuantity(stockQuantity);
        inventory.setRemainingQuantity(stockQuantity);
        inventory.setStatus(Status.AVAILABLE_IN_STOCK);
        inventory.setProduct(product);
        return new ResponseEntity<>(inventoryRepository.save(inventory), HttpStatus.CREATED);
    }
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP")
//      This updates the Inventory associated to each product
    public ResponseEntity<Inventory> updateInventory(long id, Inventory updatedInventory) throws MessagingException {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow();
        existingInventory.setStockQuantity(updatedInventory.getStockQuantity());
        existingInventory.setRemainingQuantity(updatedInventory.getRemainingQuantity());
        messageService.sendUpdatesNotification(STR."This is to notify that \{product.getProductName()} has been updated and currently has\{updatedInventory.getRemainingQuantity()}products available in stock");
        existingInventory.setStatus(Status.AVAILABLE_IN_STOCK);
        return new ResponseEntity<>(inventoryRepository.save(existingInventory), HttpStatus.ACCEPTED);
    }
//      This deletes all the inventory records of each product
    public ResponseEntity<Inventory> deleteInventory(long id) {
        Inventory deletedInventory = inventoryRepository.findById(id).orElseThrow();
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>(deletedInventory, HttpStatus.ACCEPTED);
    }

    //  Logics on the Inventory Tracking
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP")
    public ResponseEntity<String> inventoryTracking(Product product) throws MessagingException {
        List<Inventory> inventoryRecords = inventoryRepository.findByProduct(product);
        for (Inventory inventory : inventoryRecords) {

                if (inventory.getRemainingQuantity() <= 10) {
                    messageService.sendLowStockNotification(STR."This is to notify that \{product.getProductName()}is low in stock with \{inventory.getRemainingQuantity()}product(s) left");
                    inventory.setStatus(Status.LOW_STOCK);
                }
                if (inventory.getRemainingQuantity() <= 4) {
                    messageService.sendVeryLowStockNotification(STR."This is to notify that \{product.getProductName()}is low in stock with \{inventory.getRemainingQuantity()}product(s)left");
                    inventory.setStatus(Status.VERY_LOW_STOCK);
                }
                if (inventory.getRemainingQuantity() == 0) {
                    messageService.sendOutOfStockNotification(STR."This is to notify that \{product.getProductName()}is out of stock");
                    inventory.setStatus(Status.OUT_OF_STOCK);
                }
                inventoryRepository.save(inventory);

        }
        return ResponseEntity.ok("Successful");
    }
}