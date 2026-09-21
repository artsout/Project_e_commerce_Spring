package com.e_commerce.Project_E_Commerce_Spring.Dto.Client;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdatedRequest {


    @NotBlank
    private String clientName;

    @Email
    private String emailClient;

    @NotNull
    private LocalDateTime clientCreationDate;

    @NotBlank
    private String password;

    @NotBlank
    private String number;

    @NotNull
    private Address clientAddress;
}
