package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByClientName(String clientName);
    List<Client> findByEmailClient(String emailClient);
    List<Client> findByNumber(String number);
    List<Client> findByClientAddress(Address clientAddress);

    @Query("SELECT s FROM Store s JOIN s.storeFollowed f WHERE f.store.id = :storeId")
    List<Client> findByFollowingStoreId(UUID storeId);

    @Query("SELECT c FROM Client c JOIN c.clientFollow f WHERE f.store.id = :storeId")
    List<Client> findByFollowEDStoreId(@Param("storeId") UUID storeId);
}
