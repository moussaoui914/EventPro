package org.example.eventproo.exception;

public class TicketAlreadyUsedException extends RuntimeException {
    public TicketAlreadyUsedException(String message) { super(message); }
}
