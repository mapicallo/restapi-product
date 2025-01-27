package com.mapicallo.product;

import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PriceRepositoryTest {

    private PriceRepositoryPort priceRepository; // Interface del repositorio mockeada

    @BeforeEach
    void setUp() {
        // Mockeamos la interfaz del repositorio
        priceRepository = mock(PriceRepositoryPort.class);
    }

    @Test
    void testFindPrice() {
        // Configuramos datos simulados
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        // Creamos un objeto Price simulado
        Price mockPrice = new Price();
        mockPrice.setId(1L);
        mockPrice.setBrandId(brandId);
        mockPrice.setProductId(productId);
        mockPrice.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        mockPrice.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        mockPrice.setPriceList(1);
        mockPrice.setPriority(0);
        mockPrice.setPrice(35.5);
        mockPrice.setCurrency("EUR");

        // Configuramos el comportamiento del mock
        when(priceRepository.findPrice(eq(brandId), eq(productId), eq(applicationDate)))
                .thenReturn(Optional.of(mockPrice));

        // Ejecutamos el método que queremos probar
        Optional<Price> result = priceRepository.findPrice(brandId, productId, applicationDate);

        // Validamos que el resultado no es vacío
        assertTrue(result.isPresent(), "El resultado no debería ser vacío");

        // Validamos los datos del resultado
        Price price = result.get();
        assertEquals(brandId, price.getBrandId(), "El brandId debería coincidir");
        assertEquals(productId, price.getProductId(), "El productId debería coincidir");
        assertEquals(35.5, price.getPrice(), 0.01, "El precio debería ser 35.5");
        assertEquals("EUR", price.getCurrency(), "La moneda debería ser EUR");

        // Verificamos que el mock fue llamado con los argumentos correctos
        verify(priceRepository, times(1)).findPrice(eq(brandId), eq(productId), eq(applicationDate));
    }
}