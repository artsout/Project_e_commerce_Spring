package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.ClientRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {


    Client findByEmailClient(String email);
    List<Client> findByClientNameContainingIgnoreCase(String clientName);

    Client findByNumber(String number);
    List<Client> findByClientAddress(Address clientAddress);

    @Query("SELECT s FROM Store s JOIN s.storeFollowed f WHERE f.store.id = :storeId")
    List<Client> findByFollowingStoreId(UUID storeId);

    @Query("SELECT c FROM Client c JOIN c.clientFollow f WHERE f.store.id = :storeId")
    List<Client> findByFollowEDStoreId(@Param("storeId") UUID storeId);

    @Query("SELECT s FROM Client s WHERE " +
            "(:rua IS NULL OR s.clientAddress.rua LIKE %:rua%) AND " +
            "(:cidade IS NULL OR s.clientAddress.cidade LIKE %:cidade%) AND " +
            "(:cep IS NULL OR s.clientAddress.cep = :cep)")
    List<Client> findByClientAddress(
            @Param("rua") String rua,
            @Param("cidade") String cidade,
            @Param("cep") String cep
    );

    Optional<Client> findByRoles(ClientRole role);
}
