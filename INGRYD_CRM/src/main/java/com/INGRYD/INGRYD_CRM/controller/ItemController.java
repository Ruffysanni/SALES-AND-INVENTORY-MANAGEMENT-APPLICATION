package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Item;
import com.INGRYD.INGRYD_CRM.service.ItemService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/allItems")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public Optional<Item> getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId);
    }

    @PostMapping
    public Item postNewItem(Item item){
        return itemService.postNewItem(item);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(Item item, @PathVariable Long itemId){
        return itemService.updateItem(item, itemId);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(Item item, @PathVariable Long itemId){
        itemService.deleteItem(item, itemId);
    }
@PostMapping("/orderItem")
    public ResponseEntity<String> orderItem(@RequestBody Item item, @Valid long itemQuantity) throws MessagingException {
        return itemService.orderItem(item, itemQuantity);
    }
}
