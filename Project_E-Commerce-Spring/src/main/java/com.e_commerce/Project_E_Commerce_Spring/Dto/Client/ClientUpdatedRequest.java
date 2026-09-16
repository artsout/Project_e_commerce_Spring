package com.e_commerce.Project_E_Commerce_Spring.Dto.Client;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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


    private String clientName;


    private String emailClient;


    private LocalDateTime clientCreationDate;

    private String password;


    private String number;

    private Address clientAddress;
}
