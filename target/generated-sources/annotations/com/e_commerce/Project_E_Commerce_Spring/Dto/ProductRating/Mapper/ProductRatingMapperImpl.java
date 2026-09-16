package com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.Mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.ProductRatingDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-14T19:39:28-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ProductRatingMapperImpl implements ProductRatingMapper {

    @Override
    public ProductRatingDto toDto(Product_Rating product_rating) {
        if ( product_rating == null ) {
            return null;
        }

        ProductRatingDto productRatingDto = new ProductRatingDto();

        productRatingDto.setRating_id( product_rating.getParent_id() );
        productRatingDto.setIdchild( product_ratingIdchildParent_id( product_rating ) );
        productRatingDto.setClientId( product_ratingClientId( product_rating ) );
        productRatingDto.setProductId( product_ratingProductId( product_rating ) );
        productRatingDto.setCommentDate( product_rating.getCommentDate() );
        productRatingDto.setStarRating( product_rating.getStarRating() );

        return productRatingDto;
    }

    @Override
    public Product_Rating toEntity(ProductRatingDto productRatingDto) {
        if ( productRatingDto == null ) {
            return null;
        }

        Product_Rating product_Rating = new Product_Rating();

        product_Rating.setCommentDate( productRatingDto.getCommentDate() );
        product_Rating.setStarRating( productRatingDto.getStarRating() );
        product_Rating.setIdchild( map( productRatingDto.getIdchild() ) );

        return product_Rating;
    }

    private Long product_ratingIdchildParent_id(Product_Rating product_Rating) {
        Product_Rating idchild = product_Rating.getIdchild();
        if ( idchild == null ) {
            return null;
        }
        return idchild.getParent_id();
    }

    private UUID product_ratingClientId(Product_Rating product_Rating) {
        Client client = product_Rating.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }

    private Long product_ratingProductId(Product_Rating product_Rating) {
        Product product = product_Rating.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
