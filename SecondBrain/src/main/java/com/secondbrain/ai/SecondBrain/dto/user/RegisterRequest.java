package com.secondbrain.ai.SecondBrain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message ="Username Cannot be empty")
    @Size(min=3,max=20,message = "UserName must be between 3 and 20 character")
    private String username;

    @NotBlank(message ="Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message ="Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;


}
