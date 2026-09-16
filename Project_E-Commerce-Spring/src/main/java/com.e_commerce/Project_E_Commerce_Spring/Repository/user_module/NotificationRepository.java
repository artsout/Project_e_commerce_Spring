package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByNotificationNameContainingIgnoreCase(String notificationName);

    List<Notification> findByNotificationClass(Notification_Class notification_class);

    List<Notification> findByClientId(UUID userId);

    List<Notification> findByStoreId(UUID storeId);

    List<Notification> findByNotificationDateBetween(LocalDateTime inicio , LocalDateTime fim);

    @Query("SELECT n FROM Notification n WHERE n.client.id = :clientId " +
            "AND (:notificationType IS NULL OR n.notificationType = :notificationType) " +
            "AND (:notificationAlreadyRead IS NULL OR n.notificationAlreadyRead = :notificationAlreadyRead)"+
            "AND ( :notificationClass IS NULL OR n.notificationClass = :notificationClass)")
    List<Notification> findAllNotificationsByClientAndType (@Param("clientId") UUID clientId, @Param("notificationType") NotificationType notificationType , @Param("notificationAlreadyRead") Boolean notificationAlreadyRead, @Param("notificationClass") Notification_Class notificationClass);

}

