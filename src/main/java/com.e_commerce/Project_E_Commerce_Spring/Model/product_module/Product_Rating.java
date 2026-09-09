package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Product_Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parent_id;

    @NotBlank
    @Column(nullable = false,length = 500)
    private String comment;

    @CreatedDate
    private LocalDateTime comment_date;

    @NotNull
    @ColumnDefault("0")
    private Integer starRating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_rating_id_child")
    private Product_Rating productRating;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_rating_id_client")
    private Client client;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_rating_id_product")
    private Product product;


    @OneToMany(mappedBy = "parent_id",orphanRemoval = true)
    private Set<Product_Rating> childProductRatings= new HashSet<>();

    public Product_Rating(Long parent_id, String comment, LocalDateTime comment_date, Integer starRating, Product_Rating productRating, Client client, Product product, Set<Product_Rating> childProductRatings) {
        this.parent_id = parent_id;
        this.comment = comment;
        this.comment_date = comment_date;
        this.starRating = starRating;
        this.productRating = productRating;
        this.client = client;
        this.product = product;
        this.childProductRatings = childProductRatings;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getComment_date() {
        return comment_date;
    }

    public void setComment_date(LocalDateTime comment_date) {
        this.comment_date = comment_date;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Product_Rating getProductRating() {
        return productRating;
    }

    public void setProductRating(Product_Rating productRating) {
        this.productRating = productRating;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Product_Rating> getChildProductRatings() {
        return childProductRatings;
    }

    public void setChildProductRatings(Set<Product_Rating> childProductRatings) {
        this.childProductRatings = childProductRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product_Rating that = (Product_Rating) o;
        return parent_id == that.parent_id && Objects.equals(comment, that.comment) && Objects.equals(comment_date, that.comment_date) && Objects.equals(starRating, that.starRating) && Objects.equals(productRating, that.productRating) && Objects.equals(client, that.client) && Objects.equals(product, that.product) && Objects.equals(childProductRatings, that.childProductRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent_id, comment, comment_date, starRating, productRating, client, product, childProductRatings);
    }

    @Override
    public String toString() {
        return "Product_Rating{" +
                "parent_id=" + parent_id +
                ", comment='" + comment + '\'' +
                ", comment_date=" + comment_date +
                ", starRating=" + starRating +
                ", child_id=" + productRating +
                ", id_client=" + client +
                ", id_product=" + product +
                ", childProductRatings=" + childProductRatings +
                '}';
    }

    public void addChildProductRating(Product_Rating product_rating){
        childProductRatings.add(product_rating);
    }
    public void removeChildProductRating(Product_Rating product_rating){
        childProductRatings.remove(product_rating);
    }
}
