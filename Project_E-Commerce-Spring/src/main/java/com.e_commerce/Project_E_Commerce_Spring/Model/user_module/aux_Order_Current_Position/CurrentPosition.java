package com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CurrentPosition {

    private double latitude;
    private double longitude;

}
