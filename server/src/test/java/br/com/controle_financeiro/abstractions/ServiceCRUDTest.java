package br.com.controle_financeiro.abstractions;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.controle_financeiro.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.usuario.repository.UsuarioRepository;
import br.com.controle_financeiro.usuario.service.UsuarioCRUDService;

@ExtendWith(MockitoExtension.class)
class ServiceCRUDTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    UsuarioCRUDService service;

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoCreateForChamado() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("Teste");
        usuarioEntity.setDataNascimento(LocalDate.now());
        usuarioEntity.setEmail("teste@teste.com");

        service.create(usuarioEntity);

        verify(repository, times(1)).save(usuarioEntity);
    }
}
