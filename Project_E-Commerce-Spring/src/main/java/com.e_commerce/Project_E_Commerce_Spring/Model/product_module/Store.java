package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthRegisterRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.StoreRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "store",indexes = {
        @Index(name = "idx_store_adress",columnList = "store_address"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Email(message = "Please, a valid email")
    @Column(nullable = false ,unique = true)
    private String email;


    private Integer followCount=0;

    @NotBlank
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
    @Column(nullable = false,length = 14)
    private  String cnpj;

    @NotBlank
    @Column(nullable = false)
    private String storeName;


    @NotBlank
    @Column(nullable = false,unique = true)
    private String password;

    @Embedded
    private Address storeAddress;


    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer orderItemCount;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();


    @OneToMany(mappedBy = "store")
    private Set<Follow_Store> storeFollowed = new HashSet<>();

    @OneToMany(mappedBy = "store" ,cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private Set<Post> storePost = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)@JoinTable(
            name ="store_role_join",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<StoreRole> roles = new HashSet<>();



    public boolean isLoginCorrect(StoreAuthRequest storeAuthRequestRequest, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(storeAuthRequestRequest.getPassword() , this.password);
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
