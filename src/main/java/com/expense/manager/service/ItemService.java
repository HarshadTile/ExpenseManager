package com.expense.manager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.Item;
import com.expense.manager.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Save or Update Item
    public void saveItem(Item item) {
        List<Item> existingItems = itemRepository.findByItemCode(item.getItemCode());

        if (existingItems.isEmpty()) {
            // No item with this itemCode, insert new
            itemRepository.save(item);
            System.out.println("Inserted new item: " + item.getItemName());
        } else {
            // Update the latest item with this itemCode
            Item latestItem = existingItems.get(existingItems.size() - 1);
            latestItem.setItemName(item.getItemName());
            latestItem.setRate(item.getRate());
            itemRepository.save(latestItem);
            System.out.println("Updated item: " + item.getItemName());
        }
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    public boolean itemCodeExists(String itemCode) {
        return !itemRepository.findByItemCode(itemCode).isEmpty();
    }
    
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // ✅ Soft delete an item
    
    
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
    
    public void updateItem(Long id, String itemCode, String itemName, BigDecimal rate) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setItemCode(itemCode);
            item.setItemName(itemName);
            item.setRate(rate);
            itemRepository.save(item);
        } else {
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }
}
