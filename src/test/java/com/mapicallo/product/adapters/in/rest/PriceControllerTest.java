package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
import com.mapicallo.product.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    private PriceController priceController;
    private PriceApplicationService priceApplicationService;

    @BeforeEach
    void setUp() {
        // Mock del servicio
        priceApplicationService = mock(PriceApplicationService.class);
        // Instancia del controlador con el servicio mockeado
        priceController = new PriceController(priceApplicationService);
    }

    @Test
    void testGetPrice_Success() {
        // Datos de prueba
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price mockPrice = new Price(brandId, productId, applicationDate, applicationDate, 1, 0, 35.50, "EUR");

        // Configuración del mock
        when(priceApplicationService.getPrice(brandId, productId, applicationDate)).thenReturn(mockPrice);

        // Llamada al endpoint
        ResponseEntity<Price> response = priceController.getPrice(brandId, productId, applicationDate);

        // Validaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPrice, response.getBody());
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, applicationDate);
    }

    @Test
    void testGetPrice_NotFound() {
        // Datos de prueba
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        // Configuración del mock para lanzar excepción
        when(priceApplicationService.getPrice(brandId, productId, applicationDate))
                .thenThrow(new PriceNotFoundException("No se encontró un precio para el producto"));

        // Llamada al endpoint y validación de excepción
        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            priceController.getPrice(brandId, productId, applicationDate);
        });

        assertEquals("No se encontró un precio para el producto", exception.getMessage());
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, applicationDate);
    }
}
