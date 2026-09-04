package com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Embeddable
public class Address {
    @NotBlank
    private String rua;

    @NotBlank
    private String cidade;

    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String cep;

    public Address(String rua, String cidade, String cep) {
        this.rua = rua;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(rua, address.rua) && Objects.equals(cidade, address.cidade) && Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua, cidade, cep);
    }

    @Override
    public String toString() {
        return "Adress{" +
                "rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
