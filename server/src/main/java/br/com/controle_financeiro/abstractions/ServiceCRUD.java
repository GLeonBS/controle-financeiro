package br.com.controle_financeiro.abstractions;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@SuppressWarnings({"ALL"})
@Validated
@AllArgsConstructor
@Transactional
public abstract class ServiceCRUD<R extends EntityCRUD> {

    protected final RepositoryCRUD repository;
    private Class<R> entityClass = (Class<R>) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    public ServiceCRUD(RepositoryCRUD repository) {
        this.repository = repository;
    }

    public R create(@NotNull R request) {
        try {
            R entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(request, entity);
            return (R) repository.save(entity);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Page<EntityCRUD> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @SneakyThrows
    public EntityCRUD findOne(@PathVariable @NotNull UUID id) {
        return (EntityCRUD) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName(), id));
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
