package com.example.controlefinanceiro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DTOFake(@NotBlank(message = "Insira um nome!") String nome,
                      @NotBlank(message = "Insira um email!") @Email(message = "Insira um Email Valido!") String email,
                      @NotBlank(message = "Insira uma senha!") String senha) {
}
