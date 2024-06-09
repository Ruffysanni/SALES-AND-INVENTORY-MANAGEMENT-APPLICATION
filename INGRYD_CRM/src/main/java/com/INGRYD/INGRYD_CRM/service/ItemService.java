package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.exception.InsufficientStockException;
import com.INGRYD.INGRYD_CRM.exception.ProductNotFoundException;
import com.INGRYD.INGRYD_CRM.model.Inventory;
import com.INGRYD.INGRYD_CRM.model.Item;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.ItemRepository;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final MessageService messageService;
    private final Product product;

    private final InventoryService inventoryService;

    public ItemService(ItemRepository itemRepository, MessageService messageService, Product product, InventoryService inventoryService) {
        this.itemRepository = itemRepository;
        this.messageService = messageService;
        this.product = product;
        this.inventoryService = inventoryService;
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item postNewItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item itemToUpdate, Long id) {
        // CHeck if tem to be update is available in the database
        Item itemFromDb = itemRepository.findById(id).get();

        // Update the new item with the existing one
        itemToUpdate.setQuantity(itemFromDb.getQuantity());
        itemToUpdate.setUnitPrice(itemFromDb.getUnitPrice());
        return itemRepository.save(itemToUpdate);
    }

    public void deleteItem(Item item, Long id) {
        // Check if tem to be update is available in the database
        Item itemFromDb = itemRepository.findById(id).get();
        // Delete the new item from the repository
        itemRepository.delete(itemFromDb);
    }

    //  Logic for ordering
    @ConditionalOnProperty(value = "notification.role", havingValue = "CUSTOMER")
    public ResponseEntity<String> orderItem(Item item, long itemQuantity) throws MessagingException {
        List<Inventory> inventoryRecords = inventoryService.getInventoryByProduct(Long.valueOf(item.getProducts().getProductName())).getBody();
        if (inventoryRecords == null) {
            throw new ProductNotFoundException("Product not found");
        }
        for (Inventory inventory : inventoryRecords) {
            if (itemQuantity > inventory.getRemainingQuantity()) {
                int quantityLeft = inventory.getRemainingQuantity();
                messageService.sendInsufficientStockNotification(STR."This is to notify that \{product.getProductName()} has insufficient products available in stock with \{inventory.getRemainingQuantity()}product(s) left");
                throw new InsufficientStockException(STR."Not enough stock available. Only\{quantityLeft} quantities left.");
            }
            if (inventory.getRemainingQuantity() >= itemQuantity) {
                inventory.setRemainingQuantity((int) (inventory.getRemainingQuantity() - itemQuantity));
                messageService.sendSalesNotification(STR."This is to notify that \{itemQuantity} items has been sold out from \{product.getProductName()}");
            }
        }
        return ResponseEntity.ok("Success");
    }
}