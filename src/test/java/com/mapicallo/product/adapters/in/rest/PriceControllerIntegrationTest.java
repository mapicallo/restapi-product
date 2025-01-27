package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceApplicationService priceApplicationService;

    @Test
    void testGetPrice_Success() throws Exception {
        // Datos de prueba
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price mockPrice = new Price(brandId, productId, applicationDate, applicationDate, 1, 0, 35.50, "EUR");

        // Configuración del mock
        when(priceApplicationService.getPrice(brandId, productId, applicationDate)).thenReturn(mockPrice);

        // Llamada al endpoint
        mockMvc.perform(get("/prices")
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId))
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.price").value(35.50));
    }
}
