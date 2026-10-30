package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return  new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return  new ResponseEntity<>("user added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/users/v1/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return  new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

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


    @PutMapping("/users/v2/{id}")
    public ResponseEntity<UserDTO> updateUserV2(@PathVariable Long id,@RequestBody UserDTO userDTO) {

        boolean flag = userService.updateUserV2(userDTO, id);
        if (flag) {
            return ResponseEntity.ok(getUserById(id).getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
