package com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @NotNull
    private TypeOfStoreRoles typeOfStoreRoles;

    public StoreRole(TypeOfStoreRoles typeOfStoreRoles){
        this.typeOfStoreRoles=typeOfStoreRoles;
    }

}
