package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper;


import org.mapstruct.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequest clientRequest);
    ClientRequest toDtoRequest(Client client);
    ClientResponse toDtoResponse(Client client);
}
