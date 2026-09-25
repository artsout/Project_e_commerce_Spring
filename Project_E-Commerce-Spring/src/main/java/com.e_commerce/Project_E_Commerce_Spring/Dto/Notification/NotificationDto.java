package com.e_commerce.Project_E_Commerce_Spring.Dto.Notification;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {



    @NotBlank
    private String notificationName;

    private Boolean notificationAlreadyRead;
    @NotNull
    private LocalDateTime notificationDate;
    @NotNull
    private Notification_Class notificationClass;
    @NotBlank
    private String notificationDescription;

    private Long productId;
}
