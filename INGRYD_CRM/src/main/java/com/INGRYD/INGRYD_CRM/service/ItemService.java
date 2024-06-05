package com.INGRYD.INGRYD_CRM.service;

import com.INGRYD.INGRYD_CRM.model.Item;
import com.INGRYD.INGRYD_CRM.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    public Item getItemById(Long id){
        return itemRepository.findById(id).get();
    }
    public Item postNewItem(Item item){
        return itemRepository.save(item);
    }
    public Item updateItem(Item itemToUpdate, Long id){
        // CHeck if tem to be update is available in the database
        Item itemFromDb = itemRepository.findById(id).get();

        // Update the new item with the existing one
        itemToUpdate.setQuantity(itemFromDb.getQuantity());
        itemToUpdate.setUnitPrice(itemFromDb.getUnitPrice());
        return itemRepository.save(itemToUpdate);
    }

    public void deleteItem(Item item,  Long id){
        // Check if tem to be update is available in the database
        Item itemFromDb = itemRepository.findById(id).get();
        // Delete the new item from the repository
        itemRepository.delete(item);
    }
}