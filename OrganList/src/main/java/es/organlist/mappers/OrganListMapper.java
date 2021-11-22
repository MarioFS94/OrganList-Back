package es.organlist.mappers;

import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.UserDTO;
import es.organlist.model.dto.api.ProductAPIDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import java.util.List;

@Mapper
public interface OrganListMapper {

    UserDTO toUserDTO(UsuarioEntity usuarioEntity);

    ProductDTO toProductDTO(ProductEntity productEntity);

    List<ProductEntity> toProductEntities(List<ProductDTO> productDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "display_name", target = "name")
    @Mapping(target = "description", ignore = true)//productAPIDTO.getDetail().getDescription()
    @Mapping(source = "display_name", target = "price")
    //@Mapping(defaultValue = "false", target = "essential")
    @ValueMapping(source = "false", target = "essential")
    ProductDTO mapProductApiToProductDTO(ProductAPIDTO productAPIDTO);

}
