package com.ecommerce.user.services;

import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "userServiceImplV2")
public class UserServiceImplV2 implements UserServiceV2{
    Logger logger = LoggerFactory.getLogger(UserServiceImplV2.class);

    UserRepository userRepository;

    @Autowired
    public UserServiceImplV2(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> fetchAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll().stream().map(this::userToUserResponse).collect(Collectors.toList());
    }

    @Override
    public void addUser(UserRequest userDTO) {
        userRepository.save(userRequestToUser(userDTO));

    }

    @Override
    public UserResponse findById(Long id) {
        logger.info("Fetching user with id :: {}", id);
        User user = userRepository.
                findById(id).
                orElseThrow(() -> new UserNotFoundException("User with id :: " + id + "doesn't exist"));

        return userToUserResponse(user);
    }

    @Override
    public UserResponse findByIdV2(Long id) {
        return null;
    }

    @Override
    public Optional<UserResponse> findByIdV3(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserResponse> updateUser(UserRequest userDTO, UserRequest userDTO1) {
        return Optional.empty();
    }



    @Override
    public boolean updateUserV2(UserRequest userDTO, Long id) {
       logger.info("Updating user with id :: {}", id);
        User user = userRepository.
                findById(id).
                orElseThrow(() -> new UserNotFoundException("User with id :: " + id + "doesn't exist"));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setRole(user.getRole());
        logger.info(String.valueOf(user));
        logger.info(String.valueOf(user.getAddress()));
        if(user.getAddress()!=null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipCode(user.getAddress().getZipCode());
            userResponse.setAddress(addressDTO);
        }
        logger.info(String.valueOf(userResponse));
        return  userResponse;
    }


    private User userRequestToUser(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        if(userRequest.getAddress()!=null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipCode(userRequest.getAddress().getZipCode());
            user.setAddress(address);
        }
        logger.info(String.valueOf(user));
        return  user;
    }
}
