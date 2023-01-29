package br.dev.diego.medic.api.infra.exceptions;

public class TokenGenerationException extends RuntimeException {

    public TokenGenerationException(String message) {
        super(message);
    }
}
