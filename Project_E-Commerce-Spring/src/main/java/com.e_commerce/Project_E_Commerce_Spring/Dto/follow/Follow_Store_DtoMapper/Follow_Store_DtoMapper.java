package com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper;


import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface Follow_Store_DtoMapper {


    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "storeId", source = "store.id")
    Follow_Store_Dto toDto(Follow_Store follow_store);
}
