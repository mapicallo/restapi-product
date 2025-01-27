package com.mapicallo.product.adapters.in.rest.advice;

import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejo de excepciones genéricas.
     *
     * @param ex      Excepción capturada.
     * @param request Contexto de la solicitud.
     * @return ResponseEntity con un mensaje de error genérico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        Map<String, Object> body = buildErrorResponse(
                "Ocurrió un error inesperado",
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Manejo de la excepción `PriceNotFoundException`.
     *
     * @param ex      Excepción capturada.
     * @param request Contexto de la solicitud.
     * @return ResponseEntity con un mensaje específico para esta excepción.
     */
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Object> handlePriceNotFoundException(PriceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = buildErrorResponse(
                "Recurso no encontrado",
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Manejo de excepciones para errores de validación (ejemplo: argumentos inválidos).
     *
     * @param ex      Excepción capturada.
     * @param request Contexto de la solicitud.
     * @return ResponseEntity con un mensaje de error específico.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = buildErrorResponse(
                "Solicitud inválida",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Construye una respuesta de error consistente.
     *
     * @param message       Mensaje general de error.
     * @param details       Detalles específicos del error.
     * @param httpStatus    Código de estado HTTP asociado al error.
     * @return Map con los detalles del error.
     */
    private Map<String, Object> buildErrorResponse(String message, String details, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", httpStatus.value());
        body.put("error", httpStatus.getReasonPhrase());
        body.put("message", message);
        body.put("details", details);
        return body;
    }
}
