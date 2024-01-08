package com.example.abstractions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Validated
@AllArgsConstructor
@Service
public abstract class ServiceCRUD<T, R> {

    private final ObjectMapper objectMapper;
    private final T repository;

    public Page<T> list(Pageable pageable) {
        return repository.findAll(pageable).map(personMapper::toDTO);
    }

    public T findById(@PathVariable @NotNull Long id) {
        return repository.findById(id).map(personMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public T create(@Valid @NotNull T object) {
        return personMapper.toDTO(repository.save(personMapper.toEntity(object)));
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

    public void delete(@PathVariable @NotNull UUID id) {
        repository
                .delete(repository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException()));
    }
}
