package com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth;


import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreAuthRegisterRequest {

    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 30)
    @Column(nullable = false)
    private String storeName;

    @NotBlank
    @Pattern(regexp = "\\d{14}", message = "CNPJ must have 14 digits")
    private  String cnpj;

    @NotBlank
    @Size(min = 8,max = 20)
    private String password;

    @NotNull
    private Address storeAddress;
}
