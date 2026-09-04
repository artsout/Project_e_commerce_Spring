package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification_Class.Notification_Class;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByNotificationName(String notificationName);

    List<Notification> findByNotificationClass(Notification_Class notification_class);

    List<Notification> findByClientId(UUID userId);

    List<Notification> findByIdClientIdAndNotificationReadFalse(UUID userId);

    List<Notification> findByIdClientIdAndNotificationReadTrue(UUID userId);


    @EntityGraph(attributePaths = {"id_pedido", "id_store"})
    List<Notification> findByIdClientIdAndIdPedidoIsNotNull(UUID clientId);

    @EntityGraph(attributePaths = {"id_pedido", "id_store"})
    List<Notification> findByIdClientIdAndIdPedidoIsNotNullAndNotificationReadFalse(UUID clientId);
}
