package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification_Class.Notification_Class;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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


    public Notification(Long id, String notificationName, String notificationDescription, Boolean notificationAlreadyRead, LocalDateTime notificationDate, Notification_Class notificationClass, Client client, Product product, Store store) {
        this.id = id;
        this.notificationName = notificationName;
        this.notificationDescription = notificationDescription;
        this.notificationAlreadyRead = notificationAlreadyRead;
        this.notificationDate = notificationDate;
        this.notificationClass = notificationClass;
        this.client = client;
        this.product = product;
        this.store = store;
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

    public Notification_Class getNotificationClass() {
        return notificationClass;
    }

    public void setNotificationClass(Notification_Class notificationClass) {
        this.notificationClass = notificationClass;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id && Objects.equals(notificationName, that.notificationName) && Objects.equals(notificationDescription, that.notificationDescription) && Objects.equals(notificationAlreadyRead, that.notificationAlreadyRead) && Objects.equals(notificationDate, that.notificationDate) && notificationClass == that.notificationClass && Objects.equals(client, that.client) && Objects.equals(product, that.product) && Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationName, notificationDescription, notificationAlreadyRead, notificationDate, notificationClass, client, product, store);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notificationName='" + notificationName + '\'' +
                ", notificationDescription='" + notificationDescription + '\'' +
                ", notificationAlreadyRead=" + notificationAlreadyRead +
                ", notificationDate=" + notificationDate +
                ", notification_class=" + notificationClass +
                ", id_client=" + client +
                ", id_product=" + product +
                ", id_store=" + store +
                '}';
    }
}
