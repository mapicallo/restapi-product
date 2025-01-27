package com.mapicallo.product;

import com.mapicallo.product.adapters.out.persistence.PriceJpaRepository;
import com.mapicallo.product.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class PriceRepositoryIntegrationTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @Test
    void testFindPriceForDate() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        Optional<Price> price = priceJpaRepository.findPrice(brandId, productId, applicationDate);

        assertTrue(price.isPresent());
        assertEquals(1L, price.get().getId()); // ID 1 debería ser el resultado
    }
}
