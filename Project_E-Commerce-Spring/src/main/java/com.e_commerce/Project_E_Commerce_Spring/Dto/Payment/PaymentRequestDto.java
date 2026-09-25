package com.e_commerce.Project_E_Commerce_Spring.Dto.Payment;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment.PaymentLevel;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    @NotNull
    private PaymentType paymentType;


    //Mapping
    @NotNull
    private Long paymentId;
}
