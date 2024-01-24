package com.example.learn_spring_jpa.service;

import com.example.learn_spring_jpa.domain.item.Movie;
import com.example.learn_spring_jpa.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void saveItem() {

        // Given
        Movie movie = new Movie();
        movie.setName("상품명1");
        movie.setActor("배우1");

        // When
        itemService.saveItem(movie);
        entityManager.flush();
        entityManager.clear();
        movie.setActor("배우 수정");
        itemService.saveItem(movie);



        // Then
//        Assertions.assertEquals(movie, itemRepository.findOne(movie.getId()));
    }
    
}