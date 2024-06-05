package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.model.Item;
import com.INGRYD.INGRYD_CRM.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/allItems")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public Item getItemById(Long itemId){
        return itemService.getItemById(itemId);
    }

    @PostMapping
    public Item postNewItem(Item item){
        return itemService.postNewItem(item);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(Item item, Long itemId){
        return itemService.updateItem(item, itemId);
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(Item item, Long itemId){
        itemService.deleteItem(item, itemId);
    }

}
