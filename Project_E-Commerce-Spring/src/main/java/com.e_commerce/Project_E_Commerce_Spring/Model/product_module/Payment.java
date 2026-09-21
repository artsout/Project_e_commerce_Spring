package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;


import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment.PaymentLevel;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.EnumPayment.PaymentType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment", indexes = {
        @Index(name = "idx_order_id" , columnList = "fk_payment_order_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PaymentType paymentType;
    @Embedded
    private PaymentLevel paymentLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
