package com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Adress {
    @NotBlank
    private String rua;

    @NotBlank
    private String cidade;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String cep;
}
