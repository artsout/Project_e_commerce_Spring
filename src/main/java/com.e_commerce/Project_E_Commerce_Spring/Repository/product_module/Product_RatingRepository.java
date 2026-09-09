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



    List<Product_Rating> findByStarRating(Integer starRating);

    List<Product_Rating>  findByCommentDateBetween(LocalDateTime min,LocalDateTime max);


    List<Product_Rating> findByClientId(UUID userId);

    List<Product_Rating> findByProductId(Long productId);

}
