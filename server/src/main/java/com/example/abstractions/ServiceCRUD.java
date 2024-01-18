package com.example.abstractions;

import com.example.interfaces.RepositoryCRUD;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@SuppressWarnings({"ALL"})
@Validated
@AllArgsConstructor
@Service
public abstract class ServiceCRUD<R extends EntityControleFinanceiro, T> {

    private final ModelMapper modelMapper;
    private final RepositoryCRUD repository;
    private final R entity;

    //TODO
//    public Page<EntityControleFinanceiro> list(Pageable pageable) {
//        return repository.findAll(pageable).map( objectMapper.personMapper::toDTO);
//    }

    //TODO
//    public EntityControleFinanceiro findById(@PathVariable @NotNull Long id) {
//        return repository.findById(id).map(personMapper::toDTO)
//                .orElseThrow(() -> new IllegalArgumentException());
//    }

    public R create(@Valid T dto) {
        return (R) repository.save(modelMapper.map(dto, entity.getClass()));
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
