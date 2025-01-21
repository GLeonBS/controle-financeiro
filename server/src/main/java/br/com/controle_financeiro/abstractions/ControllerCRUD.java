package br.com.controle_financeiro.abstractions;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@SuppressWarnings("ALL")
@Validated
@AllArgsConstructor
public abstract class ControllerCRUD<T extends EntityCRUD> {
    private static final Pageable DEFAULT_PAGEABLE = Pageable.ofSize(20);

    protected final ServiceCRUD service;

    @PostMapping
    public ResponseEntity<T> create(@RequestBody @Valid T entity) {
        T entityCreated = (T) service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
    }

    @GetMapping
    public @ResponseBody Page<EntityCRUD> list(Pageable pageable) {
        Pageable finalPage = pageable == null ? DEFAULT_PAGEABLE : pageable;
        return service.list(finalPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityCRUD> findById(@PathVariable @NotNull UUID id) {
        return ResponseEntity.ok((EntityCRUD) service.findOne(id));
    }

    //TODO
    //    @PutMapping("/{id}")
    //    public T update(@PathVariable @NotNull Long id, @RequestBody @Valid T object) {
    //        return service.update(id, object);
    //    }
    //
    //TODO
    //    @DeleteMapping("/{id}")
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    public void delete(@PathVariable @NotNull Long id) {
    //        service.delete(id);
    //    }
}
