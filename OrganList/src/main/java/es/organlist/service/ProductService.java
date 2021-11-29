package es.organlist.service;

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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Product service.
 */
@Service
@Slf4j
public class ProductService {

    private static OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);
    /**
     * El servicio de la API de Mercadona
     */
    private final ProductAPIService productServiceApi;

    private final ProductRepository productRepository;

    private final ProductShopRepository productShopRepository;

    @Autowired
    public ProductService(ProductAPIService productServiceApi, ProductRepository productRepository, ProductShopRepository productShopRepository) {
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

    public ResponseEntity insertProduct(ProductDTO productDTO) throws Exception {
        ProductEntity newProduct;
        //Mercadona id
        Integer shopId = 1;

        if (productDTO == null) {
            throw new NotFoundException("No hay datos de entrada");
        }

        ProductEntity productEntity = mapper.toProductEntity(productDTO);
        try{
            newProduct = productRepository.save(productEntity);
        } catch (Exception e) {
            throw new Exception("Error al insertar producto");
        }

        ProductShopEntity productShopEntity = mapper.toProductShopEntity(newProduct, shopId);
        productShopRepository.save(productShopEntity);

        return new ResponseEntity("Insertado!", HttpStatus.CREATED);
    }

    public ResponseEntity deleteProduct(Integer productId) {
        try {
            productShopRepository.deleteByProduct(productId);
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + productId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Borrado!", HttpStatus.CREATED);
    }

    public ProductEntity updateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = mapper.toProductEntity(productDTO);
        ProductEntity updatedProduct;
        try {
            updatedProduct = productRepository.save(productEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + productEntity.getId() + "\n" + e.getMessage());
        }
        return updatedProduct;
    }

    public ResponseEntity updateEssentialProduct(Integer productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        //Comprobamos si hay resultado
        if (productEntity.isPresent()) {
            productEntity.get().setEssential(Boolean.TRUE);
        } else {
            throw new NotFoundException("No existe el producto con id " + productId);
        }
        try {
            productRepository.save(productEntity.get());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + productId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Modificado el valor de favorito!", HttpStatus.OK);
    }
}
