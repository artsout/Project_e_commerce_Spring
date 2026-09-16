package com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUpdateRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-16T16:02:45-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDto toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDto.NotificationDtoBuilder notificationDto = NotificationDto.builder();

        notificationDto.clientId( notificationClientId( notification ) );
        notificationDto.storeId( notificationStoreId( notification ) );
        notificationDto.productId( notificationProductId( notification ) );
        notificationDto.id( notification.getId() );
        notificationDto.notificationName( notification.getNotificationName() );
        notificationDto.notificationAlreadyRead( notification.getNotificationAlreadyRead() );
        notificationDto.notificationDate( notification.getNotificationDate() );
        notificationDto.notificationClass( notification.getNotificationClass() );
        notificationDto.notificationDescription( notification.getNotificationDescription() );

        return notificationDto.build();
    }

    @Override
    public Notification toEntity(NotificationUpdateRequest notificationUpdateRequest) {
        if ( notificationUpdateRequest == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setNotificationName( notificationUpdateRequest.getNotificationName() );
        notification.setNotificationDescription( notificationUpdateRequest.getNotificationDescription() );
        notification.setNotificationType( notificationUpdateRequest.getNotificationType() );
        notification.setNotificationAlreadyRead( notificationUpdateRequest.getNotificationAlreadyRead() );
        notification.setNotificationClass( notificationUpdateRequest.getNotificationClass() );

        return notification;
    }

    private UUID notificationClientId(Notification notification) {
        Client client = notification.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }

    private UUID notificationStoreId(Notification notification) {
        Store store = notification.getStore();
        if ( store == null ) {
            return null;
        }
        return store.getId();
    }

    private Long notificationProductId(Notification notification) {
        Product product = notification.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
