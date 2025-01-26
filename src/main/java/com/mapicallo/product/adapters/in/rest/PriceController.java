package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceApplicationService priceApplicationService;

    public PriceController(PriceApplicationService priceApplicationService) {
        this.priceApplicationService = priceApplicationService;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(@RequestParam Long brandId,
                                          @RequestParam Long productId,
                                          @RequestParam LocalDateTime applicationDate) {
        Optional<Price> price = priceApplicationService.getPrice(brandId, productId, applicationDate);
        // Si el precio no se encuentra, devolvemos un código 404 (NOT_FOUND)
        return price.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
