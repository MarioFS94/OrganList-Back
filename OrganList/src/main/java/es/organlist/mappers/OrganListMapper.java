package es.organlist.mappers;

import es.organlist.model.dto.ProductDTO;
import es.organlist.model.dto.UserDTO;
import es.organlist.model.entity.ProductEntity;
import es.organlist.model.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper
public interface OrganListMapper {

    UserDTO toUserDTO(UsuarioEntity usuarioEntity);

    ProductDTO toProductDTO(ProductEntity productEntity);
}
