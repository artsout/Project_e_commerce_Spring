package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    List<Store> findByOrderItemCount(Integer orderItemCount);

    List<Store> findByCnpj(String cnpj);

    List<Store> findByEmail(String email);

    List<Store> findByAddress(Address address);
}
