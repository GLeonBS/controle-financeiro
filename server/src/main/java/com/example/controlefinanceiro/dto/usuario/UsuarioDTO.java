package com.example.controlefinanceiro.dto.usuario;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record UsuarioDTO(@NotBlank(message = "Insira um nome") String nome,
                         @PastOrPresent(message = "Data inválida, favor não inserir uma data futura") Date dataNascimento,
                         @NotBlank(message = "Insira um Email") @Email(message = "Insira um Email Valido!") String email,
                         @NotBlank(message = "Insira uma senha") String senha, String cargo) {
}