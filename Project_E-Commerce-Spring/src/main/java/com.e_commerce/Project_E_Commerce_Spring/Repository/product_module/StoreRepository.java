package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    List<Store> findByOrderItemCount(Integer orderItemCount);

    Store findByCnpj(String cnpj);

    Store findByEmail(String email);


    @Query(value = "SELECT s.* FROM Store s " +
            "INNER JOIN StoreAddress a ON s.address_id = a.id " +
            "WHERE (:rua IS NULL OR to_tsvector('portuguese', a.rua) @@ to_tsquery('portuguese', :rua)) " +
            "AND (:cidade IS NULL OR to_tsvector('portuguese', a.cidade) @@ to_tsquery('portuguese', :cidade)) " +
            "AND (:cep IS NULL OR a.cep = :cep)",
            nativeQuery = true)
    List<Store> findByStoreAddress(
            @Param("rua") String rua,
            @Param("cidade") String cidade,
            @Param("cep") String cep
    );
}
