package br.com.controle_financeiro.modules.usuario.controller;

import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle_financeiro.config.security.dto.RecoveryJwtTokenDto;
import br.com.controle_financeiro.modules.usuario.dto.LoginUsuarioDto;
import br.com.controle_financeiro.modules.usuario.dto.UsuarioResponseDTO;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.service.UsuarioCRUDService;
import br.com.controle_financeiro.modules.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioService service;

    private UsuarioCRUDService crudService;

    @PostMapping("/login")
    @Tag(name = "Usuário", description = "Login do usuário")
    @Operation(summary = "Operação de login", description = "Essa função é resposável por devolver o token do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = RecoveryJwtTokenDto.class))
            }),
    })
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginUsuarioDto loginUserDto) {
        try {
            RecoveryJwtTokenDto token = service.authenticateUser(loginUserDto);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid UsuarioEntity entity) {
        try {
            UsuarioResponseDTO entityCreated = crudService.create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findById(@PathVariable(required = true) UUID id) {
        try {
            return ResponseEntity.ok(crudService.findOne(id));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> update(@PathVariable UUID id,
            @RequestBody(required = true) UsuarioEntity object) {
        try {
            return ResponseEntity.ok(crudService.update(id, object));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            crudService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
