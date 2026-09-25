package com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment;

import jakarta.persistence.Embeddable;

@Embeddable
public enum PaymentLevel {
    PENDING,
    PROCESSING,
    APPROVED,
    REJECTED,
    REFUNDED;
}
