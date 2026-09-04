package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification_Class.Notification_Class;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification",indexes = {
        @Index(name = "idx_client_read", columnList = "fk_notification_id_client, notification_already_read"),

        @Index(name = "idx_notification_store", columnList = "fk_notification_id_store"),

        @Index(name = "idx_notification_date", columnList = "notification_date DESC"),

        @Index(name = "idx_notification_product", columnList = "fk_notification_id_product")
})
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(nullable = false)
    private String notificationName;

    @NotBlank
    @Column(nullable = false,length = 300)
    private String notificationDescription;


    @Column(nullable = false)
    private Boolean notificationAlreadyRead;

    @CreatedDate
    private LocalDateTime notificationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private Notification_Class notification_class;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_client")
    private Client id_client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_product")
    private Product id_product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_store")
    private Store id_store;

}
