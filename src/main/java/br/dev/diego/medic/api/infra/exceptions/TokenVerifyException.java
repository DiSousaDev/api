package br.dev.diego.medic.api.infra.exceptions;

public class TokenVerifyException extends RuntimeException {

    public TokenVerifyException(String message) {
        super(message);
    }
}
