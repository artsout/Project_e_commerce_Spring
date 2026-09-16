package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface Follow_StoreRepository extends JpaRepository<Follow_Store,UUID> {

    @Query("SELECT f FROM Follow_Store f WHERE f.client.id = :clientId AND f.followStoreDate BETWEEN :dataInicio AND :dataFim")
    List<Follow_Store> findByClientIdAndFollowStoreDateBetween(@Param("clientId") UUID clientId,
                                                               @Param("dataInicio") LocalDateTime dataInicio,
                                                               @Param("dataFim") LocalDateTime dataFim
    );

    boolean existsByClientIdAndStoreId(UUID clientId, UUID storeId);
    Follow_Store findByClientIdAndStoreId(UUID clientId, UUID storeId);

    List<Follow_Store> findByClientId(UUID clientId);

    @Query("SELECT f.client FROM Follow_Store f WHERE f.store.id = :storeId")
    List<Client> findClientByStoreId(UUID storeId);


    List<Follow_Store> findByStoreId(UUID storeId);
}
