package es.organlist.service;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.ProductListDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.ProductListEntity;
import es.organlist.model.entity.ProductShopEntity;
import es.organlist.repository.ProductListRepository;
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

    private final ProductListRepository productListRepository;

    @Autowired
    public ProductService(
            ProductAPIService productServiceApi,
            ProductRepository productRepository,
            ProductShopRepository productShopRepository,
            ProductListRepository productListRepository
    ) {
        this.productServiceApi = productServiceApi;
        this.productRepository = productRepository;
        this.productShopRepository = productShopRepository;
        this.productListRepository = productListRepository;
    }

    public List<ProductDTO> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        if (productEntities.isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay productos en la BBDD");
        }
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
                throw new NotFoundException(HttpStatus.NOT_FOUND + " - No se cargaron productos");
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

        if (productsBBDD.isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay productos en la BBDD");
        }

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
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay datos de entrada");
        }

        ProductEntity productEntity = mapper.toProductEntity(productDTO);
        try{
            newProduct = productRepository.save(productEntity);
        } catch (Exception e) {
            throw new Exception("Error al insertar producto" + e.getMessage());
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
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + productId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Borrado!", HttpStatus.OK);
    }

    public ProductEntity updateProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay datos de entrada");
        }
        ProductEntity productEntity = mapper.toProductEntity(productDTO);
        ProductEntity updatedProduct;
        try {
            updatedProduct = productRepository.save(productEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + productEntity.getId() + "\n" + e.getMessage());
        }
        return updatedProduct;
    }

    public ResponseEntity updateEssentialProduct(Integer productId) {
        Optional<ProductEntity> productEntityOpt = productRepository.findById(productId);

        //Comprobamos si hay resultado
        if (productEntityOpt.isPresent()) {
            ProductEntity productEntity = productEntityOpt.get();
            productEntity.setEssential(productEntity.isEssential() ? Boolean.FALSE : Boolean.TRUE);
        } else {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + productId);
        }

        productRepository.save(productEntityOpt.get());

        return new ResponseEntity("Modificado el valor de favorito!", HttpStatus.OK);
    }

    public List<ProductListDTO> getProductByList(Integer listId) {
        List<ProductListEntity> productListEntities = productListRepository.findByList(listId);
        if (productListEntities.isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existen datos en la BBDD");
        }
        return productListEntities.stream()
                .map(productListEntity -> mapper.toProductListDTO(productListEntity))
                .collect(Collectors.toList());
    }
}
