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

    @Query("SELECT s FROM Store s WHERE " +
            "(:rua IS NULL OR s.storeAddress.rua LIKE %:rua%) AND " +
            "(:cidade IS NULL OR s.storeAddress.cidade LIKE %:cidade%) AND " +
            "(:cep IS NULL OR s.storeAddress.cep = :cep)")
    List<Store> findByStoreAddress(
            @Param("rua") String rua,
            @Param("cidade") String cidade,
            @Param("cep") String cep
    );
}
