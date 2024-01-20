package com.example.controlefinanceiro.abstractions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("ALL")
@Validated
@AllArgsConstructor
public abstract class ControllerCRUD<T> {

    protected final ServiceCRUD service;

    //TODO
//    @GetMapping
//    public @ResponseBody List<T> list(Pageable pageable) {
//        return service.list(pageable).getContent();
//    }
    //TODO
//
//    @GetMapping("/{id}")
//    public T findById(@PathVariable @NotNull Long id) {
//        return service.findById(id);
//    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityControleFinanceiro create(@RequestBody T dto) {
        return service.create(dto);
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
