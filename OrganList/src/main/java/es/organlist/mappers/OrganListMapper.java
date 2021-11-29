package es.organlist.mappers;

import es.organlist.model.dto.ListDTO;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.UserDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.model.entity.ListEntity;
import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.ProductShopEntity;
import es.organlist.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import java.util.List;

@Mapper
public interface OrganListMapper {

    UserDTO toUserDTO(UserEntity userEntity);

    ProductDTO toProductDTO(ProductEntity productEntity);

    ProductEntity toProductEntity(ProductDTO productDTO);

    @Mapping(target = "productEntity.id", ignore = true)
    @Mapping(source = "productEntity.id", target = "product")
    @ValueMapping(source = "shopId", target = "shop")
    ProductShopEntity toProductShopEntity(ProductEntity productEntity, Integer shopId);

    List<ProductEntity> toProductEntities(List<ProductDTO> productDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "display_name", target = "name")
    @Mapping(target = "description", ignore = true)//productAPIDTO.getDetail().getDescription()
    @Mapping(source = "price_instructions.unit_price", target = "price")
    @Mapping(expression = "java(productAPIDTO.getCategories().get(0).getName())", target = "category")
    @ValueMapping(source = "false", target = "essential")
    ProductDTO mapProductApiToProductDTO(ProductAPIDTO productAPIDTO);

    ListDTO toListDTO(ListEntity listEntity);

    ListEntity toListEntity(ListDTO listDTO);

    UserEntity toUserEntity(UserDTO userDTO);
}
