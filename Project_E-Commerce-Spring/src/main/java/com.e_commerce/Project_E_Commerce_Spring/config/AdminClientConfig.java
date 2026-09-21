package com.e_commerce.Project_E_Commerce_Spring.config;


import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.ClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.TypeOfClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminClientConfig implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final  ClientRoleRepository clientRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        var roleAdmin = clientRoleRepository.findByTypeOfClientRole(TypeOfClientRole.ADMIN)
                .orElseGet(() -> clientRoleRepository.save(new ClientRole(TypeOfClientRole.ADMIN)));;
        var clientAdmin = clientRepository.findByRoles(roleAdmin);
    }
}
