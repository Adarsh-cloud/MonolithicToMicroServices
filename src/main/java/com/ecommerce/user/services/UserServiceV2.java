package com.ecommerce.user.services;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserServiceV2 {
    public List<UserResponse> fetchAllUsers();
    public void addUser(UserRequest userDTO);
    public UserResponse findById(Long id);

    UserResponse findByIdV2(Long id);

    Optional<UserResponse> findByIdV3(Long id);

    Optional<UserResponse> updateUser(UserRequest userDTO, UserRequest userDTO1);

    boolean updateUserV2(UserRequest userDTO, Long id);

    void deleteUser(Long id);
}
