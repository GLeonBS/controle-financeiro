package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.abstractions.ServiceCRUD;
import com.example.controlefinanceiro.domain.EntityFake;
import com.example.controlefinanceiro.dto.DTOFake;
import com.example.controlefinanceiro.repository.RepositoryFake;
import org.springframework.stereotype.Service;

@Service
public class ServiceFake extends ServiceCRUD<EntityFake, DTOFake, RepositoryFake> {
    public ServiceFake(RepositoryFake repositoryFake) {
        super(repositoryFake);
    }
}
