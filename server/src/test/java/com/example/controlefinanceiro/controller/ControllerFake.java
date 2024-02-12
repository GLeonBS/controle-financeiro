package com.example.controlefinanceiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlefinanceiro.abstractions.ControllerCRUD;
import com.example.controlefinanceiro.dto.DTOFake;
import com.example.controlefinanceiro.service.ServiceFake;

@RestController
@RequestMapping("/controller-fake")
public class ControllerFake extends ControllerCRUD<DTOFake> {
    public ControllerFake(ServiceFake service) {
        super(service);
    }
}
