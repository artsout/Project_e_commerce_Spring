package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // ✅ CORRETO: Campo é "categoryName"
    Optional<Category> findByCategoryName(String categoryName);

    // ✅ CORRETO: Buscar categorias por nome contendo (case-insensitive)
    List<Category> findByCategoryNameContainingIgnoreCase(String namePart);
}
