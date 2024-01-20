package com.example.controlefinanceiro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UsuarioDTO(@NotBlank(message = "Insira um nome") String nome, Date dataNascimento,
                         @Email(message = "Insira um Email")String email,
                         @NotBlank(message = "Insira uma senha") String senha, String cargo){}
