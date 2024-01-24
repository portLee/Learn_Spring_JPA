package com.example.learn_spring_jpa.service;

import com.example.learn_spring_jpa.domain.item.Item;
import com.example.learn_spring_jpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item fineOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
