package com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRatingDto {

    @Null
    private Long rating_id;

    private LocalDateTime commentDate;

    private Integer starRating;

    private Long Idchild;

    private UUID clientId;

    private Long productId;
}
