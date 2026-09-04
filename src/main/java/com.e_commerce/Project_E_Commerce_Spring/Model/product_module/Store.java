package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "store",indexes = {
        @Index(name = "idx_store_adress",columnList = "store_address"),
})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Email(message = "Please, a valid email")
    @Column(nullable = false ,unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
    @Column(nullable = false,unique = false,length = 14)
    private  String cnpj;


    @Embedded
    private Address storeAddress;

    @NotNull
    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer orderItemCount;

    @OneToMany(mappedBy = "id_store",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();


    @OneToMany(mappedBy = "id_store")
    private Set<Follow_Store> storeFollowed = new HashSet<>();

    public Store(UUID id, String email, String cnpj, Address storeAddress, Integer orderItemCount, Set<Product> products, Set<Follow_Store> storeFollowed) {
        this.id = id;
        this.email = email;
        this.cnpj = cnpj;
        this.storeAddress = storeAddress;
        this.orderItemCount = orderItemCount;
        this.products = products;
        this.storeFollowed = storeFollowed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Address getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(Address storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Integer getOrderItemCount() {
        return orderItemCount;
    }

    public void setOrderItemCount(Integer orderItemCount) {
        this.orderItemCount = orderItemCount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Follow_Store> getStoreFollowed() {
        return storeFollowed;
    }

    public void setStoreFollowed(Set<Follow_Store> storeFollowed) {
        this.storeFollowed = storeFollowed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) && Objects.equals(email, store.email) && Objects.equals(cnpj, store.cnpj) && Objects.equals(storeAddress, store.storeAddress) && Objects.equals(orderItemCount, store.orderItemCount) && Objects.equals(products, store.products) && Objects.equals(storeFollowed, store.storeFollowed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, cnpj, storeAddress, orderItemCount, products, storeFollowed);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", storeAddress=" + storeAddress +
                ", orderItemCount=" + orderItemCount +
                ", products=" + products +
                ", storeFollowed=" + storeFollowed +
                '}';
    }

    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public void addStoreFollow(Follow_Store follow_store){storeFollowed.add(follow_store);}
    public  void removeStoreFollow(Follow_Store follow_store){storeFollowed.remove(follow_store);}
}
