package com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_role")
public class ClientRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @NotNull
    private TypeOfClientRole typeOfClientRole;





    public ClientRole(TypeOfClientRole typeOfClientRole){
        this.typeOfClientRole = typeOfClientRole;
    }
}
