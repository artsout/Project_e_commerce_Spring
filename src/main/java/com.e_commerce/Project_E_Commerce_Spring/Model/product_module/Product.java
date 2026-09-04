package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdk.jfr.Category;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    @JoinColumn(name = "fk_notification_id_store")
    private Store id_store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_notification_id_category")
    private Category productCategory;

    @OneToMany(mappedBy = "id_product",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Order_Item> orderItemsProduct =new HashSet<>();


    @OneToMany(mappedBy = "id_product",cascade =CascadeType.ALL,orphanRemoval = true)
    private  Set<Product_Rating> productRatings = new HashSet<>();

    public Product(long id, BigDecimal price, LocalDateTime productCreationDate, Integer productRatingCount, Integer productRating, Store id_store, Category productCategory, Set<Order_Item> orderItemsProduct, Set<Product_Rating> productRatings) {
        this.id = id;
        this.price = price;
        this.productCreationDate = productCreationDate;
        this.productRatingCount = productRatingCount;
        this.productRating = productRating;
        this.id_store = id_store;
        this.productCategory = productCategory;
        this.orderItemsProduct = orderItemsProduct;
        this.productRatings = productRatings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getProductCreationDate() {
        return productCreationDate;
    }

    public void setProductCreationDate(LocalDateTime productCreationDate) {
        this.productCreationDate = productCreationDate;
    }

    public Integer getProductRatingCount() {
        return productRatingCount;
    }

    public void setProductRatingCount(Integer productRatingCount) {
        this.productRatingCount = productRatingCount;
    }

    public Integer getProductRating() {
        return productRating;
    }

    public void setProductRating(Integer productRating) {
        this.productRating = productRating;
    }

    public Store getId_store() {
        return id_store;
    }

    public void setId_store(Store id_store) {
        this.id_store = id_store;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public Set<Order_Item> getOrderItemsProduct() {
        return orderItemsProduct;
    }

    public void setOrderItemsProduct(Set<Order_Item> orderItemsProduct) {
        this.orderItemsProduct = orderItemsProduct;
    }

    public Set<Product_Rating> getProductRatings() {
        return productRatings;
    }

    public void setProductRatings(Set<Product_Rating> productRatings) {
        this.productRatings = productRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(price, product.price) && Objects.equals(productCreationDate, product.productCreationDate) && Objects.equals(productRatingCount, product.productRatingCount) && Objects.equals(productRating, product.productRating) && Objects.equals(id_store, product.id_store) && Objects.equals(productCategory, product.productCategory) && Objects.equals(orderItemsProduct, product.orderItemsProduct) && Objects.equals(productRatings, product.productRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, productCreationDate, productRatingCount, productRating, id_store, productCategory, orderItemsProduct, productRatings);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", productCreationDate=" + productCreationDate +
                ", productRatingCount=" + productRatingCount +
                ", productRating=" + productRating +
                ", id_store=" + id_store +
                ", productCategory=" + productCategory +
                ", orderItemsProduct=" + orderItemsProduct +
                ", productRatings=" + productRatings +
                '}';
    }

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
