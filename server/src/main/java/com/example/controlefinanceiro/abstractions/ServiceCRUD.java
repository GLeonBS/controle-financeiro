package com.example.controlefinanceiro.abstractions;

import java.lang.reflect.ParameterizedType;

import org.springframework.validation.annotation.Validated;

import com.example.controlefinanceiro.interfaces.RepositoryCRUD;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings({"ALL"})
@Validated
@AllArgsConstructor
@Transactional
public abstract class ServiceCRUD<R extends EntityCRUD, T, Repository extends RepositoryCRUD> {

    protected final Repository repository;
    private Class<R> entityClass = (Class<R>) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    public ServiceCRUD(Repository repository) {
        this.repository = repository;
    }

    //TODO
    //    public Page<EntityControleFinanceiro> list(Pageable pageable) {
    //        return repository.findAll(pageable).map( objectMapper.personMapper::toDTO);
    //    }

    //TODO
    //    public EntityControleFinanceiro findById(@PathVariable @NotNull Long id) {
    //        return repository.findById(id).map(personMapper::toDTO)
    //                .orElseThrow(() -> new IllegalArgumentException());
    //    }

    @SneakyThrows
    public T create(@Valid T dto) {
        try {
            R entity = entityClass.getConstructor().newInstance();
            repository.save(entity.self(dto));
            return dto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //TODO
    //    public T update(@NotNull Long id, @Valid @NotNull T object) {
    //        object.setId(id);
    //        return repository.findById(id)
    //                .map(recordFound -> {
    //                    recordFound.setName(object.name());
    //                    recordFound.setBirthDate(object.birthDate());
    //                    recordFound.setCpf(object.cpf());
    //                    return personMapper.toDTO(repository.save(recordFound));
    //                }).orElseThrow(() -> new RecordNotFoundException(id));
    //    }

    //TODO
    //    public void delete(@PathVariable @NotNull Long id) {
    //        repository
    //                .delete(repository.findById(id)
    //                        .orElseThrow(() -> new IllegalArgumentException()));
    //    }
}
