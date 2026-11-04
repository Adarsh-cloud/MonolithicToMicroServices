package com.ecommerce.user.dto.users;

import com.ecommerce.user.model.users.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
