package com.example.controlefinanceiro.repository;

import com.example.controlefinanceiro.domain.EntityFake;
import com.example.controlefinanceiro.interfaces.RepositoryCRUD;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryFake extends RepositoryCRUD<EntityFake> {
}
