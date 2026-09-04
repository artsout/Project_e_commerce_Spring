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
import java.util.Objects;

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
    private Long id;

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


    public Notification(Long id, String notificationName, String notificationDescription, Boolean notificationAlreadyRead, LocalDateTime notificationDate, Notification_Class notification_class, Client id_client, Product id_product, Store id_store) {
        this.id = id;
        this.notificationName = notificationName;
        this.notificationDescription = notificationDescription;
        this.notificationAlreadyRead = notificationAlreadyRead;
        this.notificationDate = notificationDate;
        this.notification_class = notification_class;
        this.id_client = id_client;
        this.id_product = id_product;
        this.id_store = id_store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public Boolean getNotificationAlreadyRead() {
        return notificationAlreadyRead;
    }

    public void setNotificationAlreadyRead(Boolean notificationAlreadyRead) {
        this.notificationAlreadyRead = notificationAlreadyRead;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Notification_Class getNotification_class() {
        return notification_class;
    }

    public void setNotification_class(Notification_Class notification_class) {
        this.notification_class = notification_class;
    }

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }

    public Store getId_store() {
        return id_store;
    }

    public void setId_store(Store id_store) {
        this.id_store = id_store;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id && Objects.equals(notificationName, that.notificationName) && Objects.equals(notificationDescription, that.notificationDescription) && Objects.equals(notificationAlreadyRead, that.notificationAlreadyRead) && Objects.equals(notificationDate, that.notificationDate) && notification_class == that.notification_class && Objects.equals(id_client, that.id_client) && Objects.equals(id_product, that.id_product) && Objects.equals(id_store, that.id_store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationName, notificationDescription, notificationAlreadyRead, notificationDate, notification_class, id_client, id_product, id_store);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationName='" + notificationName + '\'' +
                ", notificationDescription='" + notificationDescription + '\'' +
                ", notificationAlreadyRead=" + notificationAlreadyRead +
                ", notificationDate=" + notificationDate +
                ", notification_class=" + notification_class +
                ", id_client=" + id_client +
                ", id_product=" + id_product +
                ", id_store=" + id_store +
                '}';
    }
}
