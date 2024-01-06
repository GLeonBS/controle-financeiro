package com.example.abstractions;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


@Validated
@RestController
@AllArgsConstructor
public abstract class ControllerCRUD<T> {

    private final ServiceCRUD service;
    @GetMapping
    public @ResponseBody List<T> list(Pageable pageable) {
        return service.list(pageable).getContent();
    }

    @GetMapping("/{id}")
    public T findById(@PathVariable @NotNull Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public T create(@RequestBody @Valid T object) {
        return service.create(object);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable @NotNull Long id, @RequestBody @Valid T object) {
        return service.update(id, object);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {
        service.delete(id);
    }
}
