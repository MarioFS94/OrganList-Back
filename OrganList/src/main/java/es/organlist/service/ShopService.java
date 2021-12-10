package es.organlist.service;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ProductShopDTO;
import es.organlist.model.dto.ShopDTO;
import es.organlist.model.entity.ListEntity;
import es.organlist.model.entity.ShopEntity;
import es.organlist.repository.ProductShopRepository;
import es.organlist.repository.ShopRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private static final OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);
    private final ShopRepository shopRepository;
    private final ProductShopRepository productShopRepository;

    public ShopService(ShopRepository shopRepository, ProductShopRepository productShopRepository) {
        this.shopRepository = shopRepository;
        this.productShopRepository = productShopRepository;
    }

    public List<ShopDTO> getShops() {
        List<ShopEntity> shopEntities = shopRepository.findAll();

        if (shopEntities.isEmpty()) {
            throw new NotFoundException("No existen tiendas en la BBDD.");
        }

        return mapper.toShopDTOList(shopEntities);
    }

    public ShopDTO getShop(Integer shopId) {
        Optional<ShopEntity> shopEntity = shopRepository.findById(shopId);
        if (!shopEntity.isPresent()) {
            throw new NotFoundException("La tienda con id " + shopId + " no existe.");
        }
        //recuperamos la tienda asociada al producto 1
        /*int productId = 1;
        getShopType(productId);*/
        return mapper.toShopDTO(shopEntity.get());
    }

    public String getShopType(int productId) {
        return null;
    }

    public ResponseEntity<String> insertShop(ShopDTO shopDTO) {
        if (shopDTO == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No hay datos de entrada");
        }
        ShopEntity shopEntity = mapper.toShopEntity(shopDTO);
        shopRepository.save(shopEntity);
        return new ResponseEntity("Insertado!", HttpStatus.CREATED);
    }

    public ResponseEntity deleteShop(Integer shopId) {
        try {
            shopRepository.deleteById(shopId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND + " - No existe el producto con id " + shopId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Borrado!", HttpStatus.OK);
    }
}
