package utils;

import java.time.LocalDate;

import br.com.controle_financeiro.enums.Role;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;

public final class Fixtures {
    public static UsuarioEntity createUsuarioEntity() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Vergil");
        usuario.setEmail("vergil@teste.com");
        usuario.setSenha("123456");
        usuario.getRoles().add(Role.ROLE_USER);
        usuario.setDataNascimento(LocalDate.of(2001, 8, 23));
        return usuario;
    }
}
