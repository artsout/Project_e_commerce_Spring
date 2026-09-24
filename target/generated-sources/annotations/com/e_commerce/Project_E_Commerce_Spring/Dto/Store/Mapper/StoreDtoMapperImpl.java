package com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.response.StoreDtoPublicResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-23T19:07:57-0300",
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
        storeDto.setStoreName( store.getStoreName() );
        storeDto.setCnpj( store.getCnpj() );
        storeDto.setPassword( store.getPassword() );
        storeDto.setStoreAddress( store.getStoreAddress() );
        storeDto.setOrderItemCount( store.getOrderItemCount() );
        storeDto.setFollowCount( store.getFollowCount() );

        return storeDto;
    }

    @Override
    public StoreUpdateDto toUpdateDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreUpdateDto storeUpdateDto = new StoreUpdateDto();

        storeUpdateDto.setEmail( store.getEmail() );
        storeUpdateDto.setCnpj( store.getCnpj() );
        storeUpdateDto.setStoreName( store.getStoreName() );
        storeUpdateDto.setPassword( store.getPassword() );
        storeUpdateDto.setStoreAddress( store.getStoreAddress() );

        return storeUpdateDto;
    }

    @Override
    public StoreDtoPublicResponse toPublicDto(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDtoPublicResponse storeDtoPublicResponse = new StoreDtoPublicResponse();

        storeDtoPublicResponse.setEmail( store.getEmail() );
        storeDtoPublicResponse.setStoreName( store.getStoreName() );
        storeDtoPublicResponse.setCnpj( store.getCnpj() );
        storeDtoPublicResponse.setStoreAddress( store.getStoreAddress() );
        storeDtoPublicResponse.setOrderItemCount( store.getOrderItemCount() );
        storeDtoPublicResponse.setFollowCount( store.getFollowCount() );

        return storeDtoPublicResponse;
    }
}
