package es.organlist.service.impl;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.ProductShopEntity;
import es.organlist.repository.ProductRepository;
import es.organlist.repository.ProductShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
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

    private final ProductShopRepository productShopRepository;

    @Autowired
    public ProductServiceImpl(ProductAPIServiceImpl productServiceApi, ProductRepository productRepository, ProductShopRepository productShopRepository) {
        this.productServiceApi = productServiceApi;
        this.productRepository = productRepository;
        this.productShopRepository = productShopRepository;
    }

    public List<ProductDTO> getProducts(/*String lang*/) {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productEntity -> mapper.toProductDTO(productEntity))
                .collect(Collectors.toList());
    }

    public ResponseEntity loadData() {
        try {
            List<ProductAPIDTO> productsAPI = productServiceApi.getAllProducts("es");
            List<ProductDTO> products = new ArrayList<>();
            List<ProductEntity> productEntities = new ArrayList<>();

            productsAPI.forEach(productAPIDTO -> {
                ProductDTO productDTO = mapper.mapProductApiToProductDTO(productAPIDTO);
                products.add(productDTO);
            });

            if (products.isEmpty()) {
                throw new NotFoundException("No se cargaron productos");
            }
            productEntities.addAll(mapper.toProductEntities(products));

            //productRepository.saveAll(productEntities);

            List<ProductShopEntity> productShopEntities = getProductShopEntities();
            //productShopRepository.saveAll(productShopEntities);
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            throw e;
        }

        return new ResponseEntity("Datos cargados correctamente.", HttpStatus.OK);
    }

    private List<ProductShopEntity> getProductShopEntities() {
        List<ProductShopEntity> productShopEntities = new ArrayList<>();

        List<ProductEntity> productsBBDD = productRepository.findAll();

        productsBBDD.forEach(productEntity -> {
            ProductShopEntity productShopEntity = new ProductShopEntity(null, productEntity.getId(), 1);
            productShopEntities.add(productShopEntity);
        });

        return productShopEntities;
    }
}
