package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category",indexes = {

})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false,unique = true)
    private String categoryName;


    @OneToMany(mappedBy = "productCategory",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Product> products =new HashSet<>();








    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
}
