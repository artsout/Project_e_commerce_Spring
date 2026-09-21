package com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreAuthRequest {

    @Email
    private String email;


    @NotBlank
    @Size(min = 8,max = 20)
    private String password;
}
