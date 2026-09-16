package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client", indexes = {
        @Index(name = "idx_client_creation_date", columnList = "client_creation_date")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(nullable = false)
    private String clientName;

    @NotBlank
    @Email(message = "Please, insert a valid email")
    @Column(nullable = false, unique = true)
    private String emailClient;

    @Column(nullable = false)
    private Integer followsCount = 0;

    @CreatedDate
    @Column(name = "client_creation_date", updatable = false, nullable = false)
    private LocalDateTime clientCreationDate;

    @NotBlank
    @Size(min = 8, max = 15, message = "Please, insert a valid password")
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+\\d{2}\\d{2}\\d{5}-\\d{4}$", message = "Valid format is +551199999-1234")
    @Column(updatable = false, unique = true, length = 15, nullable = false)
    private String number;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientPedidoCount = 0;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer clientNotificacaoCount = 0;

    @Embedded
    private Address clientAddress;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product_Rating> clientProductsRating = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Follow_Store> clientFollow = new HashSet<>();
}
