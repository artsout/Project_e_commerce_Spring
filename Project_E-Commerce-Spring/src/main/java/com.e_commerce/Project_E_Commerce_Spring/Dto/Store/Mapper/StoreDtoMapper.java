package com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreDtoMapper {

    StoreDto toDto(Store store);


}
