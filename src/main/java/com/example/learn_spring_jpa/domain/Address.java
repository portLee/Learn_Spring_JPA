package com.example.learn_spring_jpa.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // 값 타입 지정
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
