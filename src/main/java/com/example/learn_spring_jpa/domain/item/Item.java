package com.example.learn_spring_jpa.domain.item;


import com.example.learn_spring_jpa.domain.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략 사용
@DiscriminatorColumn(name = "DTYPE") // 하위 엔티티 식별 컬럼 지정
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity; // 재고 수

    @ManyToMany(mappedBy = "items") // 다대다 연관관계 명시, Category 엔티티의 items 필드를 가리킴
    private List<Category> categories = new ArrayList<>();

    /* 비즈니스 로직 */
    public void addStock(int quantity) { // 재고 증가
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) { // 재고 감소
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) { // 재고 부족한 경우
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
