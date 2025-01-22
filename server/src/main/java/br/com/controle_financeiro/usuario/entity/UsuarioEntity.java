package br.com.controle_financeiro.usuario.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.controle_financeiro.abstractions.EntityCRUD;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
