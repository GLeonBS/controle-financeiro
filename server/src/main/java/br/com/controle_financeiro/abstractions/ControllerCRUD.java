package br.com.controle_financeiro.abstractions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@SuppressWarnings("ALL")
@Validated
@AllArgsConstructor
public abstract class ControllerCRUD<T extends EntityCRUD> {

    protected final ServiceCRUD service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public T create(@RequestBody @Valid T entity) {
        return (T) service.create(entity);
    }

    @GetMapping
    public @ResponseBody Page<EntityCRUD> list(Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.ofSize(20);
        }
        return service.list(pageable);
    }
    //TODO
    //
    //    @GetMapping("/{id}")
    //    public T findById(@PathVariable @NotNull Long id) {
    //        return service.findById(id);
    //    }

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
