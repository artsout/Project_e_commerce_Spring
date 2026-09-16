package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_rating",indexes = {
        @Index(name = "idx_comment_date",columnList = "comment_date"),
        @Index(name = "idx_star_rating",columnList = "star_rating"),
        @Index(name = "idx_product_rating_id_client",columnList = "fk_product_rating_id_client"),
        @Index(name = "idx_product_rating_id_product",columnList = "fk_product_rating_id_product"),
        @Index(name = "idx_product_rating_child_id",columnList = "fk_product_rating_child_id"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parent_id;

    @NotBlank
    @Column(nullable = false,length = 500)
    private String comment;

    @CreatedDate
    private LocalDateTime commentDate;

    @NotNull
    @ColumnDefault("0")
    private Integer starRating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_rating_id_child")
    private Product_Rating Idchild;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToMany(mappedBy = "parent_id",orphanRemoval = true)
    private Set<Product_Rating> childProductRatings= new HashSet<>();


    public void addChildProductRating(Product_Rating product_rating){
        childProductRatings.add(product_rating);
    }
    public void removeChildProductRating(Product_Rating product_rating){
        childProductRatings.remove(product_rating);
    }
}
