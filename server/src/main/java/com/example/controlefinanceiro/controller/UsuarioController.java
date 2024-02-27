package com.example.controlefinanceiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controlefinanceiro.abstractions.ControllerCRUD;
import com.example.controlefinanceiro.dto.usuario.UsuarioDTO;
import com.example.controlefinanceiro.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends ControllerCRUD<UsuarioDTO> {

    public UsuarioController(UsuarioService service) {
        super(service);
    }
}
