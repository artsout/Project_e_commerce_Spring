package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_item",indexes = {
        @Index(name = "idx_order_item_quantity",columnList = "order_item_quantity"),
        @Index(name = "idx_order_item_id_product",columnList = "fk_order_item_id_product"),
        @Index(name = "idx_order_item_id_order",columnList = "fk_order_item_id_order")
})
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
    @JoinColumn(name = "fk_order_item_id_order")
    private Order id_order;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_item_id_product")
    private Product id_product;

    public Order_Item(UUID id, Integer orderItemQuantity, Order id_order, Product id_product) {
        this.id = id;
        this.orderItemQuantity = orderItemQuantity;
        this.id_order = id_order;
        this.id_product = id_product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public Order getId_order() {
        return id_order;
    }

    public void setId_order(Order id_order) {
        this.id_order = id_order;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order_Item orderItem = (Order_Item) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(orderItemQuantity, orderItem.orderItemQuantity) && Objects.equals(id_order, orderItem.id_order) && Objects.equals(id_product, orderItem.id_product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItemQuantity, id_order, id_product);
    }

    @Override
    public String toString() {
        return "Order_Item{" +
                "id=" + id +
                ", orderItemQuantity=" + orderItemQuantity +
                ", id_order=" + id_order +
                ", id_product=" + id_product +
                '}';
    }
}
