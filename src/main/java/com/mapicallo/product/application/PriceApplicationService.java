package com.mapicallo.product.application;

import com.mapicallo.product.domain.exceptions.PriceNotFoundException;
import com.mapicallo.product.domain.model.Price;
import com.mapicallo.product.domain.ports.out.PriceRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
public class PriceApplicationService {

    private static final Logger log = LoggerFactory.getLogger(PriceApplicationService.class);

    private final PriceRepositoryPort priceRepository;

    // Constructor para inyección de dependencias
    public PriceApplicationService(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Método para obtener el precio según los parámetros proporcionados.
     *
     * @param brandId         ID de la marca
     * @param productId       ID del producto
     * @param applicationDate Fecha de consulta
     * @return Price          Precio encontrado o excepción si no se encuentra
     */
    public Price getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {

        log.info("Buscando precio para: brandId={}, productId={}, applicationDate={}",
                brandId, productId, applicationDate);

        // Busca el precio en el repositorio y lanza una excepción si no lo encuentra
        return priceRepository.findPrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(
                        "No se encontró un precio para el producto " + productId +
                                " con la marca " + brandId +
                                " en la fecha " + applicationDate));
    }


}
