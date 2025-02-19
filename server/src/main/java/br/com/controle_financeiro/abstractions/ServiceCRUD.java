package br.com.controle_financeiro.abstractions;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

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
            return (R) repository.save(request);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Page<EntityCRUD> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public EntityCRUD findOne(@PathVariable @NotNull UUID id) throws Throwable {
        return (EntityCRUD) repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName(), id.toString()));
    }

    public R update(@NotNull UUID id, @NotNull R object) throws Throwable {
        this.findOne(id);
        object.setId(id);
        return (R) repository.save(object);
    }

    public void delete(@PathVariable @NotNull UUID id) throws Throwable, EntityNotFoundException {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName(), id.toString())));
    }
}
