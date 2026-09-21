package com.e_commerce.Project_E_Commerce_Spring.Dto.Notification;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUpdateRequest {

    private String notificationName;
    private String notificationDescription;
    private NotificationType notificationType;
    private Boolean notificationAlreadyRead;
    private Notification_Class notificationClass;
}
