package com.example.controlefinanceiro.abstractions;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@MappedSuperclass
public abstract class EntityCRUD<T, R> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    public abstract R self(T dto);
}
