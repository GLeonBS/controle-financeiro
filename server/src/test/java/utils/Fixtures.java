package utils;

import java.time.LocalDate;

import br.com.controle_financeiro.usuario.entity.UsuarioEntity;

public final class Fixtures {
    public static UsuarioEntity createUsuarioEntity() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Dante");
        usuario.setEmail("teste@teste.com");
        usuario.setDataNascimento(LocalDate.of(2001, 8, 23));
        return usuario;
    }
}
