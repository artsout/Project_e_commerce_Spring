package com.e_commerce.Project_E_Commerce_Spring.Dto.Store;


import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateDto {

    private String email;
    private  String cnpj;
    private Address storeAddress;

}
