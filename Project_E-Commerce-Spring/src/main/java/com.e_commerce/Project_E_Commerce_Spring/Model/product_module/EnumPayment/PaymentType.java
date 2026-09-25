package com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment;

import jakarta.persistence.Embeddable;

@Embeddable
public enum PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    PIX,
    BOLETO,
    CASH
}
