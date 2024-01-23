package com.example.learn_spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // N:1 명시
    @JoinColumn(name = "MEMBER_ID") // 조인 시 참조 컬럼 지정(FK)
    private Member member; // 주문 회원
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // OrderItem 엔티티의 order 필드를 가리킴
    private List<OrderItem> orderItems = new ArrayList<>();   /* cascade = CascadeType.ALL -> Order 엔티티의 상태변화를
                                                               OrderItem 엔티티에 반영 */

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery; // 배송 정보

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; // 주문 시간

    @Enumerated(EnumType.STRING) // 열거형(Enum) 타입 매핑, 문자열로 저장
    private OrderStatus status;

    /* 연관관계 메서드 */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
