package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.services.UserService;
import com.ecommerce.user.services.UserServiceV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/advanced")
public class UserAdvancedController {

    Logger logger = LoggerFactory.getLogger(UserAdvancedController.class);

    UserServiceV2 userService;

    @Autowired
    public UserAdvancedController(@Qualifier(value = "userServiceImplV2") UserServiceV2 userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        logger.info("Inside the getUsers()");
        return  new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userDTO){
        logger.info("Inside the createUser()");
        userService.addUser(userDTO);
        return  new ResponseEntity<>("user added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/users/v1/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        logger.info("Inside the getUserById");
        return  new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }
/*
    @GetMapping("/users/v2/{id}")
    public ResponseEntity<UserDTO> getUserByIdV2(@PathVariable Long id){

        UserDTO obtainedUserDTO = userService.findByIdV2(id);
        if(obtainedUserDTO==null){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(obtainedUserDTO);
    }


    @GetMapping("/users/v3/{id}")
    public ResponseEntity<UserDTO> getUserByIdV3(@PathVariable Long id){

        Optional<UserDTO> obtainedUserDTO = userService.findByIdV3(id);
        return obtainedUserDTO.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/users/v1/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){

        Optional<UserDTO> obtainedUserDTO = userService.findByIdV3(id);
        if(obtainedUserDTO.isPresent()){
            obtainedUserDTO=userService.updateUser(userDTO,obtainedUserDTO.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(obtainedUserDTO.get());
    }

 */
    @PutMapping("/users/v2/{id}")
    public ResponseEntity<UserResponse> updateUserV2(@PathVariable Long id,@RequestBody UserRequest userDTO) {

        logger.info("Inside the updateUserV2");
        boolean flag = userService.updateUserV2(userDTO, id);
        if (flag) {
            return ResponseEntity.ok(getUserById(id).getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        logger.info("Deleting user with id :: {}", id);
        logger.info(getUserById(id).getBody().toString());
        if(getUserById(id).getBody()!=null){
            logger.info("Deleting user with id :: {}", id);
            userService.deleteUser(id);
            return  ResponseEntity.ok("User deleted successfully");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
