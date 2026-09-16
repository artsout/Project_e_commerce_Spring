package com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.ProductRatingDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductRatingMapper {

    @Mapping(target = "rating_id", source = "parent_id")
    @Mapping(target = "idchild", source = "idchild.parent_id")
    @Mapping(source = "client.id" , target = "clientId")
    @Mapping(source = "product.id" , target = "productId")
    ProductRatingDto toDto(Product_Rating product_rating);

    default Product_Rating map(Long value) {
        if (value == null) {
            return null;
        }
        Product_Rating rating = new Product_Rating();
        rating.setParent_id(value);
        return rating;
    }


    default Long map(Product_Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getParent_id(); // ajuste para o getter correto do id
    }


    Product_Rating toEntity(ProductRatingDto productRatingDto);


}
