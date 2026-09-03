package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Enum.Product_Class;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "product",indexes = {
    @Index(name = "idx_product_price",columnList = "price"),
        @Index(name = "idx_product_class",columnList = "product_class"),
        @Index(name = "idx_product_creation_date",columnList = "product_creation_date"),
        @Index(name = "idx_product_rating",columnList = "product_rating"),
        @Index(name = "idx_product_id_store",columnList = "fk_product_id_store"),
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    @NotNull
    @Column(nullable = false)
    private DecimalFormat price;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Product_Class product_class;


    @CreatedDate
    private LocalDateTime productCreationDate;

    @NotNull
    @Column(nullable = false)
    private Integer productRatingCount;//quant de avaliaçoes

    @NotNull
    @Column(nullable = false)
    private Integer productRating;//produto nota

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Store id_store;


}
