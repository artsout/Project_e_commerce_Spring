package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_item",indexes = {
        @Index(name = "idx_order_item_quantity",columnList = "order_item_quantity"),
        @Index(name = "idx_order_item_id_product",columnList = "fk_order_item_id_product"),
        @Index(name = "idx_order_item_id_order",columnList = "fk_order_item_id_order")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Positive
    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer orderItemQuantity=1;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
