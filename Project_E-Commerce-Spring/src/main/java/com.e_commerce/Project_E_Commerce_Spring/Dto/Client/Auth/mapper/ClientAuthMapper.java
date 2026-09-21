package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthRequestRegister;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.persistence.ManyToMany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientAuthMapper {


    Client registerRequestToEntity(ClientAuthRequestRegister clientAuthRequestRegister);
}
