package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByProductPrice(BigDecimal productPrice);

    List<Product> findByProductRatingCount(Integer productRatingCount);

    List<Product> findByProductRating(Integer productRating);

    List<Product>  findByStoreId(UUID storeId);

    List<Product>  findByCategoryId(Long categoryId);

}
