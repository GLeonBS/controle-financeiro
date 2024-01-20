package com.example.controlefinanceiro.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryCRUD<T, R> extends JpaRepository<R, T> {
}
