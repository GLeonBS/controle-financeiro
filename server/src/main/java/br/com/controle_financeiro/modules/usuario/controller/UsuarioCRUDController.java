package br.com.controle_financeiro.modules.usuario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle_financeiro.abstractions.ControllerCRUD;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.service.UsuarioCRUDService;

@RestController
@RequestMapping("/usuario")
public class UsuarioCRUDController extends ControllerCRUD<UsuarioEntity> {

    public UsuarioCRUDController(UsuarioCRUDService service) {
        super(service);
    }

}
