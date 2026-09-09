package com.e_commerce.Project_E_Commerce_Spring.Model.product_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order;
import jakarta.persistence.*;
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
    private Order order;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order_item_id_product")
    private Product product;

    public Order_Item(UUID id, Integer orderItemQuantity, Order order, Product product) {
        this.id = id;
        this.orderItemQuantity = orderItemQuantity;
        this.order = order;
        this.product = product;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order_Item orderItem = (Order_Item) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(orderItemQuantity, orderItem.orderItemQuantity) && Objects.equals(order, orderItem.order) && Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItemQuantity, order, product);
    }

    @Override
    public String toString() {
        return "Order_Item{" +
                "id=" + id +
                ", orderItemQuantity=" + orderItemQuantity +
                ", id_order=" + order +
                ", id_product=" + product +
                '}';
    }
}
