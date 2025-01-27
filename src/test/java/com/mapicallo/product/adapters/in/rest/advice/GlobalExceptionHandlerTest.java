package com.mapicallo.product.adapters.in.rest.advice;

import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleGeneralException() {
        // Simula una excepción genérica
        Exception exception = new Exception("Error inesperado");
        WebRequest mockRequest = mock(WebRequest.class);

        // Llama al método del handler
        ResponseEntity<Object> response = exceptionHandler.handleGeneralException(exception, mockRequest);

        // Validaciones
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void handlePriceNotFoundException() {
        // Simula una excepción PriceNotFoundException
        PriceNotFoundException exception = new PriceNotFoundException("Precio no encontrado");
        WebRequest mockRequest = mock(WebRequest.class);

        // Llama al método del handler
        ResponseEntity<Object> response = exceptionHandler.handlePriceNotFoundException(exception, mockRequest);

        // Validaciones
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void handleIllegalArgumentException() {
        // Simula una excepción IllegalArgumentException
        IllegalArgumentException exception = new IllegalArgumentException("Parámetro inválido");
        WebRequest mockRequest = mock(WebRequest.class);

        // Llama al método del handler
        ResponseEntity<Object> response = exceptionHandler.handleIllegalArgumentException(exception, mockRequest);

        // Validaciones
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
