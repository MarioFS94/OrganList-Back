package es.organlist.service;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.UserDTO;
import es.organlist.model.entity.UserEntity;
import es.organlist.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUser(Integer userId) {
        //llamamos al repositorio
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (!userEntity.isPresent()) {
            throw new NotFoundException("El usuario con id " + userId + " no existe.");
        }
        return mapper.toUserDTO(userEntity.get());
    }

    public List<UserDTO> getUsers() {
        //llamamos al repositorio
        List<UserEntity> userList = userRepository.findAll();
        //recorremos la lista de entidades y las tranformamos a lista de dtos
        return userList.stream().map(mapper::toUserDTO).collect(Collectors.toList());//user -> mapper.toUserDTO(user)
    }

    public ResponseEntity<String> insertUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new NotFoundException("No hay datos de entrada");
        }
        UserEntity userEntity = mapper.toUserEntity(userDTO);
        userRepository.save(userEntity);
        return new ResponseEntity<>("Insertado!", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteUser(Integer userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + userId + "\n" + e.getMessage());
        }
        return new ResponseEntity<>("Borrado!", HttpStatus.CREATED);
    }

    public UserEntity updateUser(UserDTO UserDTO) {
        UserEntity userEntity = mapper.toUserEntity(UserDTO);
        UserEntity updatedUser;
        try {
            updatedUser = userRepository.save(userEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + userEntity.getId() + "\n" + e.getMessage());
        }
        return updatedUser;
    }

}
