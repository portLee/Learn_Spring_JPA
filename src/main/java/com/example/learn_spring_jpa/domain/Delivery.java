package com.example.learn_spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery") // Order 엔티티의 delivery 필드를 가리킴
    private Order order;

    @Embedded // 값 타입 사용
    private Address address;

    @Enumerated(EnumType.STRING) // 열거형(Enum) 매핑, 문자열로 저장
    private DeliveryStatus status;

    public Delivery(Address address) {
        this.address = address;
    }
}
