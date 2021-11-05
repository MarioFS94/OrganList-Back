package es.organlist.mappers;

import es.organlist.model.dto.UserDTO;
import es.organlist.model.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper
public interface OrganListMapper {

    UserDTO toUserDTO(UsuarioEntity usuarioEntity);
}
