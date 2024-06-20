package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.exception.InsufficientStockException;
import com.INGRYD.INGRYD_CRM.exception.ProductNotFoundException;
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
    static Product product;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, MessageService messageService) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.messageService = messageService;
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
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP")
    public ResponseEntity<Inventory> updateInventory(long id, Inventory updatedInventory) throws MessagingException {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow();
        existingInventory.setStockQuantity(updatedInventory.getStockQuantity());
        existingInventory.setRemainingQuantity(updatedInventory.getRemainingQuantity());
        messageService.sendUpdatesNotification(STR."This is to notify that \{product.getProductName()} has been updated and currently has\{updatedInventory.getStockQuantity()}products available in stock");
        existingInventory.setStatus(Status.AVAILABLE_IN_STOCK);
        return new ResponseEntity<>(inventoryRepository.save(existingInventory), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Inventory> deleteInventory(long id) {
        Inventory deletedInventory = inventoryRepository.findById(id).orElseThrow();
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>(deletedInventory, HttpStatus.ACCEPTED);
    }

    //  Logics on the Inventory Tracking
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP")
    public ResponseEntity<String> inventoryTracking(Product product, long itemQuantity, String receiver) throws MessagingException {
        List<Inventory> inventoryRecords = inventoryRepository.findByProduct(product);
        for (Inventory inventory : inventoryRecords) {
            if (inventory != null) {
                if (itemQuantity > inventory.getRemainingQuantity()) {
                    int quantityLeft = inventory.getRemainingQuantity();
                    messageService.sendInsufficientStockNotification(STR."This is to notify that \{product.getProductName()} has insufficient products available in stock with \{inventory.getRemainingQuantity()}product(s) left", receiver);
                    throw new InsufficientStockException(STR."Not enough stock available. Only\{quantityLeft} quantities left.");
                }
                if (inventory.getRemainingQuantity() >= itemQuantity) {
                    inventory.setRemainingQuantity((int) (inventory.getRemainingQuantity() - itemQuantity));
                    messageService.sendSalesNotification(STR."This is to notify that \{itemQuantity} items has been sold out from \{product.getProductName()}", receiver);
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
            } else {
                throw new ProductNotFoundException("Product not found in Inventory");
            }
        }
        return ResponseEntity.ok("Successful");
}
}