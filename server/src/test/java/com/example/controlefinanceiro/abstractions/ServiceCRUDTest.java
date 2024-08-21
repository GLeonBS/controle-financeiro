package com.example.controlefinanceiro.abstractions;

import com.example.controlefinanceiro.domain.EntityFake;
import com.example.controlefinanceiro.dto.DTOFake;
import com.example.controlefinanceiro.repository.RepositoryFake;
import com.example.controlefinanceiro.service.ServiceFake;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.controlefinanceiro.fixtures.FixtureDTOs.createDTOFake;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceCRUDTest {
    @Mock
    private RepositoryFake repositoryFake;

    @InjectMocks
    private ServiceFake serviceFake;

    @Test
    void deveChamarOMetodoSaveDoRepositorioQuandoOMetodoCreateForChamado() {
        DTOFake dtoFake = createDTOFake();
        EntityFake expectedEntity = new EntityFake(dtoFake.nome(), dtoFake.email(), dtoFake.senha());

        serviceFake.create(dtoFake);

        verify(repositoryFake, times(1)).save(expectedEntity);
    }
}
