package com.mapicallo.product.adapters.in.rest.advice;

import com.mapicallo.product.adapters.in.rest.PriceController;
import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.exceptions.PriceNotFoundException;

import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceApplicationService priceApplicationService;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceApplicationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceNotFoundException() throws Exception {
        // Simula que no se encuentra un precio en el repositorio
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.parse("2025-01-01T00:00:00");

        when(priceApplicationService.getPrice(brandId, productId, applicationDate))
                .thenThrow(new PriceNotFoundException(
                        "No se encontró un precio para el producto " + productId +
                                " con la marca " + brandId +
                                " en la fecha " + applicationDate));

        // Realiza la llamada al endpoint y verifica la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("brandId", brandId.toString())
                        .param("productId", productId.toString())
                        .param("applicationDate", applicationDate.toString()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        "No se encontró un precio para el producto " + productId +
                                " con la marca " + brandId +
                                " en la fecha " + applicationDate))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}