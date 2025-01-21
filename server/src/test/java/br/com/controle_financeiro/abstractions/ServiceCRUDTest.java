package br.com.controle_financeiro.abstractions;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import br.com.controle_financeiro.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.usuario.repository.UsuarioRepository;
import br.com.controle_financeiro.usuario.service.UsuarioCRUDService;
import utils.Fixtures;

@ExtendWith(MockitoExtension.class)
class ServiceCRUDTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    UsuarioCRUDService service;

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoCreateForChamado() {

        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();

        service.create(usuarioEntity);

        verify(repository, times(1)).save(usuarioEntity);
    }

    @Test
    void deveChamarOMetodoFindAllDoRepositorioQuandoOMetodoListForChamado() {

        Pageable pageable = Pageable.unpaged();
        service.list(pageable);

        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void deveChamarOMetodoFindByIdDoRepositorioQuandoOMetodoFindOneForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();
        usuarioEntity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        service.findOne(id);

        verify(repository, times(1)).findById(id);
    }

    @Test
    void deveRetornarUmaExceptionQuandoOMetodoFindOneForChamado() {
        UUID id = UUID.randomUUID();

        Assertions.assertThatThrownBy(() -> service.findOne(id)).isInstanceOf(EntityNotFoundException.class)
                .hasMessage("UsuarioEntity: " + id + " não encontrado");
    }

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoUpdateForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();
        usuarioEntity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        service.update(id, usuarioEntity);

        verify(repository, times(1)).save(usuarioEntity);
    }

    @Test
    void deveRetornarUmaExceptionQuandoOMetodoUpdateForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();

        Assertions.assertThatThrownBy(() -> service.update(id, usuarioEntity))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("UsuarioEntity: " + id + " não encontrado");
    }

    @Test
    void deveChamarOMetodoDeleteDoRepositorioQuandoOMetodoDeleteForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();
        usuarioEntity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        service.delete(id);

        verify(repository, times(1)).delete(usuarioEntity);
    }

    @Test
    void deveRetornarUmaExceptionQuandoOMetodoDeleteForChamado() {
        UUID id = UUID.randomUUID();

        Assertions.assertThatThrownBy(() -> service.delete(id)).isInstanceOf(EntityNotFoundException.class)
                .hasMessage("UsuarioEntity: " + id + " não encontrado");
    }
}
