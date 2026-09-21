package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "post" , indexes = {
        @Index(name = "idx_store_id" , columnList = "fk_post_id_store"),
        @Index(name = "idx_product_id", columnList = "fk_post_id_product")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


}
