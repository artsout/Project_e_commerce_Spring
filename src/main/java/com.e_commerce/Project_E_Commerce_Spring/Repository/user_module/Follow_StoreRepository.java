package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface Follow_StoreRepository extends JpaRepository<Follow_Store,UUID> {

    List<Follow_Store> findByIdClientIdAndFollowStoreDateBetween(UUID clientId, LocalDateTime min, LocalDateTime max);

    List<Follow_Store> findByIdStoreIdAndFollowStoreDateBetween(UUID storeId, LocalDateTime min, LocalDateTime max);

    @EntityGraph(attributePaths = {"id_client"})
    List<Follow_Store> findByIdStoreId(UUID storeId);

    @EntityGraph(attributePaths = {"id_store"})
    List<Follow_Store> findByIdClientId(UUID clientId);
}
