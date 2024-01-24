package com.example.learn_spring_jpa.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@DiscriminatorValue("A") // 자식 엔티티 식별 값
public class Album extends Item {
    private String artist;
    private String etc;
}
