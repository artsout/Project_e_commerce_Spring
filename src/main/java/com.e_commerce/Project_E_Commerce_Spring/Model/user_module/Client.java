package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Adress;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client",indexes = {
        @Index(name = "idx_clent_creation_date",columnList = "client_creation_date"),
        @Index(name = "idx_client_address",columnList = "client_address")


})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(nullable = false)
    private String clientName;


    @NotBlank
    @Email(message = "Please,insert a valid email")
    @Column(nullable = false,unique = true)
    private String emailClient;


    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime clientCreationDate;

    @NotBlank
    @Size(min = 8,max = 15,message = "Please,insert a valid password")
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp ="\\d{13}")
    @Column(updatable = false,unique = true,length = 13)
    private String number;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientPedidoCount;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientNotificacaoCount;


    @Embedded
    private Adress clientAddress;

    @OneToMany(mappedBy = "id_client",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "id_product",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> productNotifications =new HashSet<>();

    @OneToMany(mappedBy = "id_store",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Notification> storeNotification=new HashSet<>();


    @OneToMany(mappedBy = "id_client",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product_Rating> clientProductsRating =new HashSet<>();

    @OneToMany(mappedBy = "id_client")
    private Set<Follow_Store> clientFollow=new HashSet<>();



    public  void addNotification(Notification notification){
        notifications.add(notification);
    }
    public  void removeNotification(Notification notification){
        notifications.remove(notification);
    }
    public  void addProductNotification(Notification notification){
        productNotifications.add(notification);
    }
    public  void removeProductNotification(Notification notification){
        productNotifications.remove(notification);
    }
    public  void addStoreNotification(Notification notification){
        storeNotification.add(notification);
    }
    public  void removeStoreNotification(Notification notification){
        storeNotification.remove(notification);
    }
    public  void addClientProductsRating(Product_Rating product_rating){
        clientProductsRating.add(product_rating);
    }
    public  void removeClientProductsRating(Product_Rating product_rating){clientProductsRating.remove(product_rating);}
    public void addClientFollow(Follow_Store follow_store){clientFollow.add(follow_store);}
    public  void removeClientFollow(Follow_Store follow_store){clientFollow.remove(follow_store);}
}
