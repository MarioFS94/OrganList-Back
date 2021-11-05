package es.organlist.service;

import es.organlist.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();
}
