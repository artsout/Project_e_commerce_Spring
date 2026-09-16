package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification",indexes = {
        @Index(name = "idx_client_read", columnList = "fk_notification_id_client, notification_already_read"),

        @Index(name = "idx_notification_store", columnList = "fk_notification_id_store"),

        @Index(name = "idx_notification_date", columnList = "notification_date DESC"),

        @Index(name = "idx_notification_product", columnList = "fk_notification_id_product")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(nullable = false)
    private String notificationName;

    @NotBlank
    @Column(nullable = false,length = 300)
    private String notificationDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private NotificationType notificationType;

    @Column(nullable = false)
    private Boolean notificationAlreadyRead;

    @CreatedDate
    private LocalDateTime notificationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private Notification_Class notificationClass;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
