package com.exercicio5apicadastroveiculos.infrastructure.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String mensagem) {
    super(mensagem);
  }
}