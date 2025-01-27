package com.mapicallo.product.application;

import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceApplicationServiceTest {

    private PriceRepositoryPort priceRepositoryPort;
    private PriceApplicationService priceApplicationService;

    @BeforeEach
    void setUp() {
        // Mock del repositorio
        priceRepositoryPort = mock(PriceRepositoryPort.class);
        // Servicio bajo prueba
        priceApplicationService = new PriceApplicationService(priceRepositoryPort);
    }

    @Test
    void testGetPrice_Success() {
        // Datos de prueba
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price mockPrice = new Price(brandId, productId, applicationDate, applicationDate, 1, 0, 35.50, "EUR");

        // Configuración del mock
        when(priceRepositoryPort.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Llamada al método y validaciones
        Price result = priceApplicationService.getPrice(brandId, productId, applicationDate);
        assertNotNull(result);
        assertEquals(brandId, result.getBrandId());
        assertEquals(productId, result.getProductId());
        assertEquals(35.50, result.getPrice());
    }

    @Test
    void testGetPrice_NotFound() {
        // Datos de prueba
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        // Configuración del mock (devuelve vacío)
        when(priceRepositoryPort.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.empty());

        // Llamada al método y validaciones
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class,
                () -> priceApplicationService.getPrice(brandId, productId, applicationDate));

        assertEquals("No se encontró un precio para el producto 35455 con la marca 1 en la fecha 2020-06-14T10:00",
                exception.getMessage());
    }
}

