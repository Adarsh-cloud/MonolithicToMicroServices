package com.ecommerce.user.services;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    List<UserDTO> usersList= new ArrayList<>();
    Long counter=0L;

    @Override
    public List<UserDTO> fetchAllUsers() {
        return usersList;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDTO.setId(++counter);
        usersList.add(userDTO);
    }

    @Override
    public UserDTO findById(Long id) {

        return usersList.stream().filter(userDTO -> userDTO.getId() == id).findFirst().
                orElseThrow(() ->new UserNotFoundException("User with id :: " + id + " doesnt exist"));
    }

    @Override
    public UserDTO findByIdV2(Long id) {
        for(UserDTO userDTO:usersList){
            if(userDTO.getId().equals(id)){
                return userDTO;
            }
        }
        return null;
    }

    @Override
    public Optional<UserDTO> findByIdV3(Long id) {
        return usersList.stream().filter(n->n.getId().equals(id)).findFirst();

    }

    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO, UserDTO userDTO1) {
        userDTO1.setFirstName(userDTO.getFirstName());
        userDTO1.setLastName(userDTO.getLastName());
        return Optional.of(userDTO1);
    }
}
