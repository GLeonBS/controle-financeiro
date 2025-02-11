package br.com.controle_financeiro.abstractions;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@SuppressWarnings("ALL")
@Validated
@AllArgsConstructor
public abstract class ControllerCRUD<T extends EntityCRUD> {
    private static final Pageable DEFAULT_PAGEABLE = Pageable.ofSize(20);

    protected final ServiceCRUD service;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<T> create(@RequestBody(required = true) @Valid T entity) {
        T entityCreated = (T) service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
    }

    @GetMapping
    @SecurityRequirement(name = "jwt_auth")
    public @ResponseBody Page<EntityCRUD> list(Pageable pageable) {
        Pageable finalPage = pageable == null ? DEFAULT_PAGEABLE : pageable;
        return service.list(finalPage);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<EntityCRUD> findById(@PathVariable(required = true) UUID id) {
        try {
            return ResponseEntity.ok((EntityCRUD) service.findOne(id));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<EntityCRUD> update(@PathVariable(required = true) UUID id,
            @RequestBody(required = true) T object) {
        try {
            return ResponseEntity.ok(service.update(id, object));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Void> delete(@PathVariable(required = true) UUID id) {
        try {
            service.delete(id);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }
}
