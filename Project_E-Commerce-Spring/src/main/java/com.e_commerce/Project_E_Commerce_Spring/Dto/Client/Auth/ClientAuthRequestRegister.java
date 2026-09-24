package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAuthRequestRegister {

    @NotBlank
    private String clientName;

    @Email
    private String emailClient;

    @NotBlank
    @Size(min = 8, max = 15, message = "Please, insert a valid password")
    private String password;

    @NotBlank
    private String number;

    @NotNull
    @Valid
    private Address clientAddress;
}
