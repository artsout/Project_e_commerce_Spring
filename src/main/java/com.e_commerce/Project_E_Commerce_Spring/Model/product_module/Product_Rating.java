package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_rating",indexes = {
        @Index(name = "idx_comment_date",columnList = "comment_date"),
        @Index(name = "idx_star_rating",columnList = "star_rating"),
        @Index(name = "idx_product_rating_id_client",columnList = "fk_product_rating_id_client"),
        @Index(name = "idx_product_rating_id_product",columnList = "fk_product_rating_id_product"),
        @Index(name = "idx_product_rating_child_id",columnList = "fk_product_rating_child_id"),
})
public class Product_Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long parent_id;

    @NotBlank
    @Column(nullable = false,length = 500)
    private String comment;

    @CreatedDate
    private LocalDateTime comment_date;

    @NotNull
    @ColumnDefault("0")
    private Integer starRating;


    @ManyToOne(fetch = FetchType.LAZY)
    private Product_Rating child_id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Client id_client;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Product id_product;


}
