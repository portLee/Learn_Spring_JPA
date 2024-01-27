package com.example.learn_spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /* 생성 메서드 */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) { //...은 가변 인자(varargs)를 나타내는 문법
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());

        return order;
    }

    /* 비즈니스 로직 */
    public void cancel() { // 주문 취소
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    
    /* 조회 로직 */
    public int getTotalPrice() { // 전체 주문 가격 조회
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

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
