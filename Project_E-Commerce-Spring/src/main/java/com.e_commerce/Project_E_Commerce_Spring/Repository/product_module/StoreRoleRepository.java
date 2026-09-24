package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.StoreRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.TypeOfStoreRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRoleRepository extends JpaRepository<StoreRole,Long> {



    Optional<StoreRole> findByTypeOfStoreRoles(TypeOfStoreRoles typeOfStoreRoles);
}
