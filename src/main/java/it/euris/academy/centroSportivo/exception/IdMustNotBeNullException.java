package it.euris.academy.centroSportivo.exception;

public class IdMustNotBeNullException extends RuntimeException{

    public IdMustNotBeNullException() {
        super("Id MUST NOT be null. You submitted a Dto with a missing Id.");
    }

    public IdMustNotBeNullException(String message) {
        super(message);
    }
}
