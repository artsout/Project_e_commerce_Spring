package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product",indexes = {
    @Index(name = "idx_product_price",columnList = "price"),
        @Index(name = "idx_product_id_category",columnList = "fk_product_id_category"),
        @Index(name = "idx_product_creation_date",columnList = "product_creation_date"),
        @Index(name = "idx_product_rating",columnList = "product_rating"),
        @Index(name = "idx_product_id_store",columnList = "fk_product_id_store"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;


    @CreatedDate
    private LocalDateTime productCreationDate;

    @NotNull
    @Column(nullable = false)
    private Integer productRatingCount;//quant de avaliaçoes

    @NotNull
    @Column(nullable = false)
    private Integer productRating;//produto nota

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Order_Item> orderItemsProduct =new HashSet<>();


    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL,orphanRemoval = true)
    private  Set<Product_Rating> productRatings = new HashSet<>();

   @OneToMany(mappedBy = "product" ,cascade = {CascadeType.MERGE , CascadeType.PERSIST})
   private Set<Post> productPosted = new HashSet<>();


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
