package com.expense.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.manager.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemCode(String itemCode);
    
    Optional<Item> findById(Long id);
    void deleteById(Long id);

}