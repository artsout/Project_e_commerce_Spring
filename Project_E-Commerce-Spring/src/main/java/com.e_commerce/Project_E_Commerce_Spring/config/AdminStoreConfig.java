package com.e_commerce.Project_E_Commerce_Spring.config;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.TypeOfStoreRoles;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;


import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class AdminStoreConfig implements CommandLineRunner {

    private StoreRoleRepository storeRoleRepository;
    private StoreRepository storeRepository;

    @Transactional
    @Override
    public  void run(String... args){
        var roleStoreAdmin = storeRoleRepository.findByTypeOfStoreRoles(TypeOfStoreRoles.ADMIN).orElseThrow(() -> new NoSuchElementException("Not found this role"));
        var storeAdmin = storeRepository.findByRoles(roleStoreAdmin);
    }
}
