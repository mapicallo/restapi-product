package com.mapicallo.product.application;

import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceApplicationService {

    private final PriceRepositoryPort priceRepository;

    // Constructor explícito para inyección de dependencias
    public PriceApplicationService(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findPrice(brandId, productId, applicationDate);
    }
}
