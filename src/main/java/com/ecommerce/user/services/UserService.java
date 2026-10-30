package com.ecommerce.user.services;

import com.ecommerce.user.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserDTO> fetchAllUsers();
    public void addUser(UserDTO userDTO);
    public UserDTO findById(Long id);

    UserDTO findByIdV2(Long id);

    Optional<UserDTO> findByIdV3(Long id);

    Optional<UserDTO> updateUser(UserDTO userDTO, UserDTO userDTO1);
}
