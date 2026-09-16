package com.e_commerce.Project_E_Commerce_Spring.Dto.Client;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private UUID id;


    private LocalDateTime clientCreationDate;


    private Integer clientNotificacaoCount;


    private Integer clientPedidoCount;


    private String clientName;


    private String emailClient;


    private String password;


    private String number;

    private Address clientAddress;
}
