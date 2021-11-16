package es.organlist.service.impl;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Product service.
 */
@Service
@Slf4j
public class ProductServiceImpl {

    private static OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);
    /**
     * El servicio de la API de Mercadona
     */
    private final ProductAPIServiceImpl productServiceApi;

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductAPIServiceImpl productServiceApi, ProductRepository productRepository) {
        this.productServiceApi = productServiceApi;
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getProducts(String lang) {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productEntity -> mapper.toProductDTO(productEntity))
                .collect(Collectors.toList());
    }
}
