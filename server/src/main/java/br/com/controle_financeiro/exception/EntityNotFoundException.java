package br.com.controle_financeiro.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String classe, UUID id) {
        super(classe + ": " + id + " não encontrado");
    }

    public EntityNotFoundException(String classe, String identificador) {
        super(classe + ": " + identificador + " não encontrado");
    }
}
