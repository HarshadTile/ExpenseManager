package com.expense.manager.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;	
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.manager.model.Item;
import com.expense.manager.repository.ItemRepository;
import com.expense.manager.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String showItemsPage(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items";  // Redirects to items.html
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(
            @RequestParam String itemCode,
            @RequestParam String itemName,
            @RequestParam BigDecimal rate) {

        Item item = new Item();
        item.setItemCode(itemCode);
        item.setItemName(itemName);
        item.setRate(rate);

        itemService.saveItem(item);
        return ResponseEntity.ok("Item saved successfully!");
    }
    
    @GetMapping("/check-code")
    public ResponseEntity<Boolean> checkItemCode(@RequestParam String itemCode) {
        boolean exists = itemService.itemCodeExists(itemCode);
        return ResponseEntity.ok(exists);
    }
    
    @GetMapping("/view")
    public String viewItems(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "view_items"; // Load view_items.html
    }

    // ✅ Soft Delete Item
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.deleteById(id);
            return "Item deleted successfully";
        } 
        return "Item not found";
    }



    
 // ✅ Load Edit Page
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "edit_item";
        }
        return "redirect:/items/view";
    }

    // ✅ Update Item
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestParam String itemCode, 
                                             @RequestParam String itemName, @RequestParam BigDecimal rate) {
        itemService.updateItem(id, itemCode, itemName, rate);
        return ResponseEntity.ok("Item updated successfully");
    }

}
