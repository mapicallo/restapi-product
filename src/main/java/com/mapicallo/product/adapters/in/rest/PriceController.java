package com.mapicallo.product.adapters.in.rest;

import com.mapicallo.product.application.GetPriceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetPriceService getPriceService;

    public PriceController(GetPriceService getPriceService) {
        this.getPriceService = getPriceService;
    }

    @GetMapping
    public String getPrice(@RequestParam Long brandId, @RequestParam Long productId, @RequestParam String date) {
        // Lógica del endpoint
        return "Price";
    }
}
