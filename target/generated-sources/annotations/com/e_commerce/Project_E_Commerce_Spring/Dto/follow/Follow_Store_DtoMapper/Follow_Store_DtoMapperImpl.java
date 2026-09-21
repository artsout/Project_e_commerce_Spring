package com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-20T23:03:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class Follow_Store_DtoMapperImpl implements Follow_Store_DtoMapper {

    @Override
    public Follow_Store_Dto toDto(Follow_Store follow_store) {
        if ( follow_store == null ) {
            return null;
        }

        Follow_Store_Dto follow_Store_Dto = new Follow_Store_Dto();

        follow_Store_Dto.setClientId( follow_storeClientId( follow_store ) );
        follow_Store_Dto.setStoreId( follow_storeStoreId( follow_store ) );
        follow_Store_Dto.setId( follow_store.getId() );
        follow_Store_Dto.setFollowStoreDate( follow_store.getFollowStoreDate() );

        return follow_Store_Dto;
    }

    private UUID follow_storeClientId(Follow_Store follow_Store) {
        Client client = follow_Store.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }

    private UUID follow_storeStoreId(Follow_Store follow_Store) {
        Store store = follow_Store.getStore();
        if ( store == null ) {
            return null;
        }
        return store.getId();
    }
}
