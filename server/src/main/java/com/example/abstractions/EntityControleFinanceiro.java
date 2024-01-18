package com.example.abstractions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@EqualsAndHashCode(of = {"id"})
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntityControleFinanceiro{
    @Id
    @GeneratedValue
    private Long id;
}
