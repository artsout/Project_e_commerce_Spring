package com.e_commerce.Project_E_Commerce_Spring.Dto.Store;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {


    @NotBlank
    private String email;
    @NotBlank
    private String storeName;
    @NotBlank
    private  String cnpj;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Valid
    private Address storeAddress;

    private Integer orderItemCount;

    private Integer followCount;
}
