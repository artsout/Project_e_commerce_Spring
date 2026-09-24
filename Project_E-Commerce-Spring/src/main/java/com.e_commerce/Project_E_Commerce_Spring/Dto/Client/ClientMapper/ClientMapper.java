package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.responses.ClientAdminResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.responses.ClientUserResponse;
import org.mapstruct.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequest clientRequest);
    ClientRequest toDtoRequest(Client client);
    ClientAdminResponse toDtoResponse(Client client);
    ClientUserResponse toDtoUserResponse(Client client);
}
