package com.example.abstractions;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("ALL")
@Validated
@RestController
@AllArgsConstructor
public class ControllerCRUD<T> {

    private final ServiceCRUD service;

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
    public EntityControleFinanceiro create(@RequestBody @Valid T dto) {
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
