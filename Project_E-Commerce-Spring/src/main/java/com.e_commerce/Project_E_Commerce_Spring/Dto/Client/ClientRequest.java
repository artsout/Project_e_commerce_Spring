package com.e_commerce.Project_E_Commerce_Spring.Dto.Client;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    @NotBlank
    private String clientName;

    @NotBlank
    private String emailClient;

    @NotBlank
    private String number;

    @NotNull
    private Address clientAddress;
}
