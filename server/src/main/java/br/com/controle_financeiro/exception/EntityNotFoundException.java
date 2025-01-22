package br.com.controle_financeiro.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String classe, UUID id) {
        super(classe + ": " + id + " não encontrado");
    }
}
