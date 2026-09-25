package com.e_commerce.Project_E_Commerce_Spring.Dto.Order_Item;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Order_ItemMapper {

    @Mapping(source = "productId",target = "product.id")
    Order_ItemDto toDto(Order_Item orderItem);

    @Mapping(source = "product.id",target = "productId")
    Order_Item toEntity(Order_ItemDto orderItemDto);
}
