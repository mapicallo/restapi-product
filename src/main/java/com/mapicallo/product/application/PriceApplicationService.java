package com.mapicallo.product.application;

import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
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

    public Price getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findPrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(
                        "No se encontró un precio para el producto " + productId +
                                " con la marca " + brandId +
                                " en la fecha " + applicationDate));
    }
}
