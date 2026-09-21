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

    @Null(message = "O campo clientId deve ser nulo. O sistema gerencia esse ID automaticamente.")
    private Long id;

    @NotNull
    private NotificationType type;
    @NotBlank
    private String notificationName;

    private Boolean notificationAlreadyRead;
    @NotNull
    private LocalDateTime notificationDate;
    @NotNull
    private Notification_Class notificationClass;
    @NotBlank
    private String notificationDescription;

    @Null(message = "O campo clientId deve ser nulo. O sistema gerencia esse ID automaticamente.")
    private UUID clientId;
    @Null(message = "O campo storeId deve ser nulo. Use o ID da URL.")
    private UUID storeId;


    private Long productId;
}
