package com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-16T16:27:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class StoreDtoMapperImpl implements StoreDtoMapper {

    @Override
    public StoreDto toDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        storeDto.setId( store.getId() );
        storeDto.setEmail( store.getEmail() );
        storeDto.setCnpj( store.getCnpj() );
        storeDto.setStoreAddress( store.getStoreAddress() );

        return storeDto;
    }
}
