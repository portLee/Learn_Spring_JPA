package com.example.learn_spring_jpa.service;

import com.example.learn_spring_jpa.domain.item.Movie;
import com.example.learn_spring_jpa.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        movie.setName("상품명1");
        movie.setActor("배우1");

        // When
        itemService.saveItem(movie);

        // Then
        Assertions.assertEquals(movie, itemRepository.findOne(movie.getId()));
    }
    
}