package org.example.eventproo.exception;

public class ReservationExpiredException extends RuntimeException {
    public ReservationExpiredException(String message) { super(message); }
}
