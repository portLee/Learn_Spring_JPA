package com.example.learn_spring_jpa.service;

import com.example.learn_spring_jpa.domain.item.Movie;
import com.example.learn_spring_jpa.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void saveItem() {

        // Given
        Movie movie = new Movie();
        movie.setName("상품1");
        movie.setActor("배우1");

        // When
        Long itemId = itemService.saveItem(movie);

        // Then
        assertEquals(movie, itemRepository.findOne(itemId));
    }
    
}