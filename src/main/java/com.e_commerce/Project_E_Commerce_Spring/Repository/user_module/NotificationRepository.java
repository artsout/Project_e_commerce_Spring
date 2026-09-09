package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification_Class.Notification_Class;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByNotificationName(String notificationName);

    List<Notification> findByNotificationClass(Notification_Class notification_class);

    List<Notification> findByClientId(UUID userId);

    @Query("SELECT n FROM Notification n WHERE n.client.id = :userId AND n.notificationAlreadyRead = false")
    List<Notification> findUnreadNotificationsByClient(@Param("userId") UUID userId);

    @Query("SELECT n FROM Notification n WHERE n.client.id = :userId AND n.notificationAlreadyRead = true")
    List<Notification> findReadNotificationsByClient(@Param("userId") UUID userId);

    @Query("SELECT n FROM Notification n WHERE n.client.id = :clientId AND n.notificationAlreadyRead = false")
    List<Notification> findUnreadPedidoNotificationsByClient(@Param("clientId") UUID clientId);

    @Query("SELECT n FROM Notification n WHERE n.client.id = :clientId AND n.store IS NOT NULL AND n.notificationAlreadyRead = false")
    List<Notification> findUnreadStoreNotificationsByClient(@Param("clientId") UUID clientId);
}

