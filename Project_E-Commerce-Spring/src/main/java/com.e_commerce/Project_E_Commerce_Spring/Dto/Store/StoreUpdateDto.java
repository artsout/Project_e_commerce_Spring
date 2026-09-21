package com.e_commerce.Project_E_Commerce_Spring.Dto.Store;


import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateDto {

    @Email
    private String email;
    @NotBlank
    private  String cnpj;

    @NotBlank
    private String storeName;

    private Address storeAddress;

}
