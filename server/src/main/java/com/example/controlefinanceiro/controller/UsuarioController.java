package com.example.controlefinanceiro.controller;

import com.example.controlefinanceiro.abstractions.ControllerCRUD;
import com.example.controlefinanceiro.dto.UsuarioDTO;
import com.example.controlefinanceiro.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends ControllerCRUD<UsuarioDTO> {

    public UsuarioController(UsuarioService service) {
        super(service);
    }
}
