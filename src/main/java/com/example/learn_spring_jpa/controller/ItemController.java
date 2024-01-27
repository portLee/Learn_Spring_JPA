package com.example.learn_spring_jpa.controller;

import com.example.learn_spring_jpa.domain.item.Book;
import com.example.learn_spring_jpa.domain.item.Item;
import com.example.learn_spring_jpa.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /* 상품 목록 */
    @GetMapping("/items")
    public String list(Model model) {

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /* 상품 등록 폼 */
    @GetMapping("/items/new")
    public String createItemForm() {
        return "items/createItemForm";
    }

    /* 상품 등록 */
    @PostMapping("/items/new")
    public String createItem(Book item) {

        itemService.saveItem(item);
        return "redirect:/items";
    }
}
