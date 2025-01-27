package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.PriceApplicationService;
import com.mapicallo.product.domain.model.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceApplicationService priceApplicationService;

    // Constructor para inyección de dependencias
    public PriceController(PriceApplicationService priceApplicationService) {
        this.priceApplicationService = priceApplicationService;
    }

    /**
     * Endpoint para obtener el precio de un producto en una fecha y hora específicas.
     *
     * @param brandId         ID de la marca
     * @param productId       ID del producto
     * @param applicationDate Fecha y hora de consulta
     * @return ResponseEntity con el precio o un código 404 si no se encuentra
     */
    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        // Obtiene el precio llamando al servicio
        Price price = priceApplicationService.getPrice(brandId, productId, applicationDate);
        return ResponseEntity.ok(price); // Devuelve un código 200 junto con el objeto Price
    }
}
