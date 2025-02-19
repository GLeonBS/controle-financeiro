package br.com.controle_financeiro.abstractions;

import java.util.UUID;

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
    public ResponseEntity<Object> create(@RequestBody(required = true) @Valid T entity) {
        try {
            T entityCreated = (T) service.create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> list(Pageable pageable) {
        try {
            Pageable finalPage = pageable == null ? DEFAULT_PAGEABLE : pageable;
            return ResponseEntity.ok(service.list(finalPage));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findById(@PathVariable(required = true) UUID id) {
        try {
            return ResponseEntity.ok((EntityCRUD) service.findOne(id));
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> update(@PathVariable(required = true) UUID id,
            @RequestBody(required = true) T object) {
        try {
            return ResponseEntity.ok(service.update(id, object));
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> delete(@PathVariable(required = true) UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
