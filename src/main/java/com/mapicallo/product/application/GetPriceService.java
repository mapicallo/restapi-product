package com.mapicallo.product.application;

import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetPriceService {
    private final PriceRepositoryPort priceRepository;

    public GetPriceService(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.findPrice(brandId, productId, date);
    }
}
