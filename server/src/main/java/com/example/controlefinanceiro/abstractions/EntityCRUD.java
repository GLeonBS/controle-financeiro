package com.example.controlefinanceiro.abstractions;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@Setter
@MappedSuperclass
public abstract class EntityCRUD<T, R> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
}
