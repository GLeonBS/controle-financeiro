package br.com.controle_financeiro.modules.usuario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import br.com.controle_financeiro.abstractions.EntityCRUD;
import br.com.controle_financeiro.modules.usuario.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "usuario")
public class UsuarioEntity extends EntityCRUD {

    @NotBlank(message = "Insira um nome!")
    private String nome;

    @Column(name = "data_nascimento")
    @PastOrPresent(message = "Data inválida, favor não inserir uma data futura")
    private LocalDate dataNascimento;

    @NotBlank(message = "Insira um Email")
    @Email(message = "Insira um Email Valido!")
    private String email;

    @NotBlank(message = "Insira uma senha")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    private String senha;

    @NotEmpty(message = "Insira ao menos um perfil")
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
