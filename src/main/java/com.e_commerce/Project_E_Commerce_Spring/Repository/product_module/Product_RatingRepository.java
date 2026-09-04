package com.e_commerce.Project_E_Commerce_Spring.Repository.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface Product_RatingRepository extends JpaRepository<Product_Rating , Long> {


    List<Product_Rating> findByParent_idId(Long parentId);


    List<Product_Rating> findByProductRating(Integer productRating);

    List<Product_Rating>  findByProductRatingDateBetween(LocalDateTime min,LocalDateTime max);

    List<Product_Rating> findByProductRatingIdAllChild(Long productRatingId);

    List<Product_Rating> findByUserIdClient(UUID userId);

    List<Product_Rating> findByIdProduct(Long productId);
    
}
