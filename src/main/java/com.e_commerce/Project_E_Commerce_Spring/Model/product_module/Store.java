package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Adress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

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
    private Adress storeAddress;

    @NotNull
    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer orderItemCount;

}
