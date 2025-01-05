package br.com.controle_financeiro.usuario.entity;

import java.util.Date;

import br.com.controle_financeiro.abstractions.EntityCRUD;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity extends EntityCRUD {

    @NotBlank(message = "Insira um nome")
    private String nome;

    @PastOrPresent(message = "Data inválida, favor não inserir uma data futura")
    private Date dataNascimento;

    @NotBlank(message = "Insira um Email")
    @Email(message = "Insira um Email Valido!")
    private String email;

    @NotBlank(message = "Insira uma senha")
    private String senha;

    private String cargo;
}
