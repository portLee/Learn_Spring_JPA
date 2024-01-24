package com.example.learn_spring_jpa.repository;

import com.example.learn_spring_jpa.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager entityManager;

    // 상품 저장 및 수정
    public void save(Item item) { // 식별자를 자동 생성 해야함.
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    // 식별자로 상품 조회
    public Item findOne(Long id) { // 상품 조회
        return entityManager.find(Item.class, id);
    }

    // 상품 전체 조회
    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i",
                        Item.class).getResultList();
    }
}
