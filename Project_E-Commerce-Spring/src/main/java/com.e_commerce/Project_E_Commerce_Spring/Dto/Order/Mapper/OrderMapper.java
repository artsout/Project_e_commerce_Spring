package com.e_commerce.Project_E_Commerce_Spring.Dto.Order.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.OrderRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Order.Response.OrderResponseDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "clientId",target = "client.id")
    OrderRequestDto toRequestDto(Order order);
    @Mapping(source = "clientId",target = "client.id")
    OrderResponseDto toResponseDto(Order order);
}
