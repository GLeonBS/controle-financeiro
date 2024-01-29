package com.example.controlefinanceiro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
public record UsuarioDTO(@NotBlank(message = "Insira um nome") String nome,
                         @PastOrPresent(message = "Data inválida, favor não inserir uma data futura")Date dataNascimento,
                         @Email(message = "Insira um Email")String email,
                         @NotBlank(message = "Insira uma senha") String senha, String cargo){}