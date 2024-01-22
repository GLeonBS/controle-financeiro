package com.example.controlefinanceiro.abstractions;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@MappedSuperclass
public abstract class EntityControleFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
