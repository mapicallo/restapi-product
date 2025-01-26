package com.mapicallo.product.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
    private Long id;
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;

    // Constructor, getters y setters
}
