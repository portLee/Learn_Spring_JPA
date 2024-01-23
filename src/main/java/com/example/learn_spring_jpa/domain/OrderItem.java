package com.example.learn_spring_jpa.domain;

import com.example.learn_spring_jpa.domain.item.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // N:1 연관 관계 명시, 지연 로딩
    @JoinColumn(name = "ITEM_ID")
    private Item item; // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID") // 참조 컬럼 지정
    private Order order; // 주문

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
}
