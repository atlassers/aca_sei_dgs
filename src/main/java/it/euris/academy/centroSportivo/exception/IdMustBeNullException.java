package it.euris.academy.centroSportivo.exception;

public class IdMustBeNullException extends RuntimeException{

    public IdMustBeNullException() {
        super("Id MUST be null. You submitted a Dto with an Id already present.");
    }

    public IdMustBeNullException(String message) {
        super(message);
    }
}
