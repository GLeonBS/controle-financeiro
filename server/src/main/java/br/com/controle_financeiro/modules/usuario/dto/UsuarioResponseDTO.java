package br.com.controle_financeiro.modules.usuario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.controle_financeiro.modules.usuario.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private List<Role> roles;
    private LocalDateTime createdAt;
}
