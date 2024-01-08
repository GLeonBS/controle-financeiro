package com.example.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UsuarioDTO(@NotBlank(message = "Insira um nome") String nome, Date dataNascimento,
                         @NotBlank(message = "Insira um Email")String email,
                         @NotBlank(message = "Insira uma senha") String senha, String cargo){}
