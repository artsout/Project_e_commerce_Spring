package com.e_commerce.Project_E_Commerce_Spring.Repository.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.ClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.TypeOfClientRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRoleRepository extends JpaRepository<ClientRole , Long> {

   Optional<ClientRole> findByTypeOfClientRole(TypeOfClientRole typeOfClientRole);

}
