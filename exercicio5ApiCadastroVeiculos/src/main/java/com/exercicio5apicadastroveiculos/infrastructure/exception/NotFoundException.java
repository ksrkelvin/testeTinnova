package com.exercicio5apicadastroveiculos.infrastructure.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}

