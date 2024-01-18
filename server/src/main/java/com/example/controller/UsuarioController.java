package com.example.controller;

import com.example.abstractions.ControllerCRUD;
import com.example.dto.UsuarioDTO;
import com.example.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/usuario")
public class UsuarioController extends ControllerCRUD<UsuarioDTO> {
    public UsuarioController(UsuarioService service) {
        super(service);
    }
}
