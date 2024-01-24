package com.example.learn_spring_jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("B") // 자식 엔티티 식별 값
public class Book extends Item {
    private String author;
    private String isbn;
}
