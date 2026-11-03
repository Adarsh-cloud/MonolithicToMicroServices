package com.ecommerce.user.dto;

import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserResponse {
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
