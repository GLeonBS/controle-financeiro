package br.com.controle_financeiro.usuario.entity;

import java.time.LocalDate;

import br.com.controle_financeiro.abstractions.EntityCRUD;
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

    @PastOrPresent(message = "Data inválida, favor não inserir uma data futura")
    private LocalDate dataNascimento;

    @NotBlank(message = "Insira um Email")
    @Email(message = "Insira um Email Valido!")
    private String email;
}
