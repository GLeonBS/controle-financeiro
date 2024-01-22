package com.example.controlefinanceiro.abstractions;

import com.example.controlefinanceiro.interfaces.RepositoryCRUD;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings({"ALL"})
@Validated
@AllArgsConstructor
public abstract class ServiceCRUD<R extends EntityControleFinanceiro, T, Repository extends RepositoryCRUD> {

    protected ModelMapper modelMapper = new ModelMapper();
    protected final Repository repository;
    private Class<T> dtoClass = (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    private Class<R> entityClass = (Class<R>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    public ServiceCRUD(Repository repository) {
        this.repository = repository;
    }

    //TODO
//    public Page<EntityControleFinanceiro> list(Pageable pageable) {
//        return repository.findAll(pageable).map( objectMapper.personMapper::toDTO);
//    }

    //TODO
//    public EntityControleFinanceiro findById(@PathVariable @NotNull Long id) {
//        return repository.findById(id).map(personMapper::toDTO)
//                .orElseThrow(() -> new IllegalArgumentException());
//    }

    public T create(@Valid T dto) {
        return (T) modelMapper.map(repository.save(modelMapper.map(dto, entityClass)), dtoClass);
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
