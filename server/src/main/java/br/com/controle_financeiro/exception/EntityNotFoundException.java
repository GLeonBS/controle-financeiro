package br.com.controle_financeiro.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String classe, String identificador) {
        super(classe + ": " + identificador + " não encontrado");
    }
}
