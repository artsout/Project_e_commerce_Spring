package com.e_commerce.Project_E_Commerce_Spring.Dto.Store;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    @Null
    private UUID id;
    @NotBlank
    private String email;
    @NotBlank
    private String storeName;
    @NotBlank
    private  String cnpj;
    private Address storeAddress;

}
