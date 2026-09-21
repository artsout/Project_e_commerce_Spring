package com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "store_address", indexes = {
        @Index(name = "idx_fts_rua", columnList = "FULLTEXT rua"),
        @Index(name = "idx_fts_cidade", columnList = "FULLTEXT cidade")
})
public class Address {
    @NotBlank
    private String rua;

    @NotBlank
    private String cidade;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String cep;

}
