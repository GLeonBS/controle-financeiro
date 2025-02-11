package br.com.controle_financeiro.modules.usuario.dto;

import java.util.List;
import java.util.UUID;

import br.com.controle_financeiro.enums.Role;

public record RecoveryUsuarioDto(
        UUID id,
        String email,
        List<Role> roles
) {
}
