package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.Category;
import org.springframework.data.annotation.CreatedDate;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product",indexes = {
    @Index(name = "idx_product_price",columnList = "price"),
        @Index(name = "idx_product_id_category",columnList = "fk_product_id_category"),
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


    @CreatedDate
    private LocalDateTime productCreationDate;

    @NotNull
    @Column(nullable = false)
    private Integer productRatingCount;//quant de avaliaçoes

    @NotNull
    @Column(nullable = false)
    private Integer productRating;//produto nota

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_store")
    private Store id_store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_category")
    private Category productCategory;

    @OneToMany(mappedBy = "id_product",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Order_Item> orderItemsProduct =new HashSet<>();


    @OneToMany(mappedBy = "id_product",cascade =CascadeType.ALL,orphanRemoval = true)
    private  Set<Product_Rating> productRatings = new HashSet<>();




    public  void addOrderItem(Order_Item orderItem){
        orderItemsProduct.add(orderItem);
    }
    public  void removeOrderItem(Order_Item orderItem){
        orderItemsProduct.remove(orderItem);
    }
    public  void addProductProductsRating(Product_Rating product_rating){productRatings.add(product_rating);}
    public  void removeProductProductsRating(Product_Rating product_rating){
        productRatings.remove(product_rating);
    }


}
