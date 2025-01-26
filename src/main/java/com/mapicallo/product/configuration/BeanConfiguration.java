package com.mapicallo.product.configuration;

import com.mapicallo.product.application.GetPriceService;
import com.mapicallo.product.adapters.out.persistence.PriceJpaRepository;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceRepositoryPort priceRepositoryPort() {
        return new PriceJpaRepository();
    }

    @Bean
    public GetPriceService getPriceService(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceService(priceRepositoryPort);
    }
}
