package br.com.controle_financeiro.modules.usuario.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import br.com.controle_financeiro.modules.usuario.dto.UsuarioResponseDTO;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;
import utils.Fixtures;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceCRUDTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    UsuarioCRUDService service;

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoCreateForChamado() {

        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();

        when(repository.save(usuarioEntity)).thenReturn(usuarioEntity);

        UsuarioResponseDTO usuarioResponseDTO = service.create(usuarioEntity);

        verify(repository, times(1)).save(usuarioEntity);

        Assertions.assertThat(usuarioResponseDTO).isNotNull();
        Assertions.assertThat(usuarioResponseDTO.getNome()).isEqualTo(usuarioEntity.getNome());
        Assertions.assertThat(usuarioResponseDTO.getEmail()).isEqualTo(usuarioEntity.getEmail());
        Assertions.assertThat(usuarioResponseDTO.getDataNascimento()).isEqualTo(usuarioEntity.getDataNascimento());
        Assertions.assertThat(usuarioResponseDTO.getRoles()).isEqualTo(usuarioEntity.getRoles());
        Assertions.assertThat(usuarioResponseDTO.getCreatedAt()).isEqualTo(usuarioEntity.getCreatedAt());
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
                .hasMessage("Usuário: " + id + " não encontrado");
    }

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoUpdateForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();
        usuarioEntity.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        when(repository.save(usuarioEntity)).thenReturn(usuarioEntity);

        UsuarioResponseDTO update = service.update(id, usuarioEntity);

        verify(repository, times(1)).save(usuarioEntity);

        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getNome()).isEqualTo(usuarioEntity.getNome());
        Assertions.assertThat(update.getEmail()).isEqualTo(usuarioEntity.getEmail());
        Assertions.assertThat(update.getDataNascimento()).isEqualTo(usuarioEntity.getDataNascimento());
        Assertions.assertThat(update.getRoles()).isEqualTo(usuarioEntity.getRoles());
        Assertions.assertThat(update.getCreatedAt()).isEqualTo(usuarioEntity.getCreatedAt());
    }

    @Test
    void deveRetornarUmaExceptionQuandoOMetodoUpdateForChamado() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();

        Assertions.assertThatThrownBy(() -> service.update(id, usuarioEntity))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Usuário: " + id + " não encontrado");
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
                .hasMessage("Usuário: " + id + " não encontrado");
    }
}
