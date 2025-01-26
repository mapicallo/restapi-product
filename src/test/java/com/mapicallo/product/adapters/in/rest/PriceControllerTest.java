package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceRepositoryPort priceRepository;

    @Test
    @DisplayName("Test 1: Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() throws Exception {
        // Mockeamos la respuesta del repositorio
        when(priceRepository.findPrice(1L, 35455L, LocalDateTime.parse("2020-06-14T10:00:00")))
                .thenReturn(Optional.of(Price.builder()
                        .brandId(1L)
                        .productId(35455L)
                        .price(35.50)
                        .priority(0)
                        .build()));

        // Simulamos la petición HTTP y verificamos la respuesta
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455));
    }

    @Test
    @DisplayName("Test 2: Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2() throws Exception {
        // Mockeamos la respuesta del repositorio
        when(priceRepository.findPrice(1L, 35455L, LocalDateTime.parse("2020-06-14T16:00:00")))
                .thenReturn(Optional.of(Price.builder()
                        .brandId(1L)
                        .productId(35455L)
                        .price(25.45)
                        .priority(1)
                        .build()));

        // Simulamos la petición HTTP y verificamos la respuesta
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455));
    }

    @Test
    @DisplayName("Test 3: Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3() throws Exception {
        // Mockeamos la respuesta del repositorio
        when(priceRepository.findPrice(1L, 35455L, LocalDateTime.parse("2020-06-14T21:00:00")))
                .thenReturn(Optional.of(Price.builder()
                        .brandId(1L)
                        .productId(35455L)
                        .price(35.50)
                        .priority(0)
                        .build()));

        // Simulamos la petición HTTP y verificamos la respuesta
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455));
    }

    @Test
    @DisplayName("Test 4: Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4() throws Exception {
        // Mockeamos la respuesta del repositorio
        when(priceRepository.findPrice(1L, 35455L, LocalDateTime.parse("2020-06-15T10:00:00")))
                .thenReturn(Optional.of(Price.builder()
                        .brandId(1L)
                        .productId(35455L)
                        .price(30.50)
                        .priority(1)
                        .build()));

        // Simulamos la petición HTTP y verificamos la respuesta
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455));
    }

    @Test
    @DisplayName("Test 5: Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5() throws Exception {
        // Mockeamos la respuesta del repositorio
        when(priceRepository.findPrice(1L, 35455L, LocalDateTime.parse("2020-06-16T21:00:00")))
                .thenReturn(Optional.empty()); // No hay resultados esperados para esta consulta

        // Simulamos la petición HTTP y verificamos la respuesta
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Se espera un 404 Not Found
    }
}
