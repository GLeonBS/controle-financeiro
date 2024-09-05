package com.example.controlefinanceiro.abstractions;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.example.controlefinanceiro.interfaces.RepositoryCRUD;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings({"ALL"})
@Validated
@AllArgsConstructor
@Transactional
public abstract class ServiceCRUD<R extends EntityCRUD, T, TRepository extends RepositoryCRUD> {

    protected final TRepository repository;
    private Class<R> entityClass = (Class<R>) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    public ServiceCRUD(TRepository repository) {
        this.repository = repository;
    }

    public Page<EntityCRUD> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    //TODO
    //    public EntityControleFinanceiro findById(@PathVariable @NotNull Long id) {
    //        return repository.findById(id).map(personMapper::toDTO)
    //                .orElseThrow(() -> new IllegalArgumentException());
    //    }

    @SneakyThrows
    public R create(@Valid @NotNull T dto) {
        try {
            R entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return (R) repository.save(entity);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
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
