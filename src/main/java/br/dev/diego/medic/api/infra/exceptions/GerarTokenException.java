package br.dev.diego.medic.api.infra.exceptions;

public class GerarTokenException extends RuntimeException {

    public GerarTokenException(String message) {
        super(message);
    }
}
