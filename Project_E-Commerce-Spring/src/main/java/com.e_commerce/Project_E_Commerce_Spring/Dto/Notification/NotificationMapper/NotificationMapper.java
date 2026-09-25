package com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUpdateRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationUserDtoResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "notificationType", target = "type")
    @Mapping(target = "clientId",source = "client.id")
    @Mapping(target = "storeId" ,source = "store.id")
    @Mapping(target = "productId" ,source = "product.id")
    NotificationDto toDto(Notification notification);

    @Mapping(source = "notificationType", target = "type")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "product.id", target = "productId")
    NotificationUserDtoResponse toUserDto(Notification notification);
    Notification toEntity(NotificationUpdateRequest notificationUpdateRequest);
}
