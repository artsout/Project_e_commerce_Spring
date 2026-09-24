package com.e_commerce.Project_E_Commerce_Spring.Dto.Notification;


import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUserDtoResponse {
    private NotificationType type;
    private String notificationName;
    private Boolean notificationAlreadyRead;
    private LocalDateTime notificationDate;
    private Notification_Class notificationClass;
    private String notificationDescription;
    private UUID clientId;
    private UUID storeId;
    private Long productId;

}
