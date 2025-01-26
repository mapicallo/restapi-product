package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PriceApplicationServiceTest {

    private PriceApplicationService priceApplicationService;

    @Mock // Mock del repositorio
    private PriceRepositoryPort priceRepository;

    @BeforeEach
    void setUp() {
        // Inicializamos los mocks y creamos la instancia del servicio con el mock del repositorio
        MockitoAnnotations.openMocks(this);
        priceApplicationService = new PriceApplicationService(priceRepository);
    }

    @Test
    void test1() {
        // Test para la petición a las 10:00 del día 14
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        Price mockPrice = new Price();
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setPrice(35.50);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));

        // Configuración del mock
        when(priceRepository.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Ejecución del método
        Optional<Price> result = priceApplicationService.getPrice(brandId, productId, applicationDate);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(mockPrice, result.get());
        assertEquals(35.50, result.get().getPrice());
    }

    @Test
    void test2() {
        // Test para la petición a las 16:00 del día 14
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00");

        Price mockPrice = new Price();
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setPrice(25.45);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));

        // Configuración del mock
        when(priceRepository.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Ejecución del método
        Optional<Price> result = priceApplicationService.getPrice(brandId, productId, applicationDate);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(mockPrice, result.get());
        assertEquals(25.45, result.get().getPrice());
    }

    @Test
    void test3() {
        // Test para la petición a las 21:00 del día 14
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00");

        Price mockPrice = new Price();
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setPrice(35.50);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));

        // Configuración del mock
        when(priceRepository.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Ejecución del método
        Optional<Price> result = priceApplicationService.getPrice(brandId, productId, applicationDate);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(mockPrice, result.get());
        assertEquals(35.50, result.get().getPrice());
    }

    @Test
    void test4() {
        // Test para la petición a las 10:00 del día 15
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T10:00:00");

        Price mockPrice = new Price();
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setPrice(30.50);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));

        // Configuración del mock
        when(priceRepository.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Ejecución del método
        Optional<Price> result = priceApplicationService.getPrice(brandId, productId, applicationDate);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(mockPrice, result.get());
        assertEquals(30.50, result.get().getPrice());
    }

    @Test
    void test5() {
        // Test para la petición a las 21:00 del día 16
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");

        Price mockPrice = new Price();
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setPrice(38.95);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));

        // Configuración del mock
        when(priceRepository.findPrice(brandId, productId, applicationDate)).thenReturn(Optional.of(mockPrice));

        // Ejecución del método
        Optional<Price> result = priceApplicationService.getPrice(brandId, productId, applicationDate);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(mockPrice, result.get());
        assertEquals(38.95, result.get().getPrice());
    }
}
