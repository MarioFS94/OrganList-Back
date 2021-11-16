package es.organlist.service.impl;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.UserDTO;
import es.organlist.model.entity.UsuarioEntity;
import es.organlist.repository.UserRepository;
import es.organlist.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private static OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getUsers() {
        //llamamos al repositorio
        List<UsuarioEntity> userList = userRepository.findAll();
        //recorremos la lista de entidades y las tranformamos a lista de dtos
        return userList.stream().map(user -> mapper.toUserDTO(user)).collect(Collectors.toList());
    }
}
