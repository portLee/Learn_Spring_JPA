package com.example.learn_spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded // 값 타입 사용
    private Address address;

    @OneToMany(mappedBy = "member") // 1:N 연관 관계의 주인을 명시, Order 엔티티의 member 필드를 가리킴
    private List<Order> orders = new ArrayList<>();
}
