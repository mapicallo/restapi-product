package com.mapicallo.product.domain.exceptions;

/**
 * Excepción personalizada para manejar casos en los que no se encuentra un precio.
 */
public class PriceNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado.
     *
     * @param message Mensaje descriptivo de la excepción.
     */
    public PriceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message Mensaje descriptivo de la excepción.
     * @param cause   Causa de la excepción (otra excepción encadenada).
     */
    public PriceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
