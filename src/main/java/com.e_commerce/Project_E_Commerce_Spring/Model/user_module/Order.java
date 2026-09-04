package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.sql.results.graph.collection.internal.CollectionAssembler;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order",indexes = {
    @Index(name = "idx_order_id_client",columnList = "fk_order_id_client"),
        @Index(name = "idx_order_status",columnList = "order_status"),
        @Index(name = "idx_order_date",columnList = "fk_order_date"),
        @Index(name = "idx_order_current_position",columnList = "fk_order_current_position"),
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Order_Status order_status;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Client id_client;


    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderExchangePeriod;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderDate;


    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime orderArrivalDate;

    @NotNull
    @Column(nullable = false)
    private Point orderCurrentPosition;


    @OneToMany(mappedBy = "id_order", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Order_Item> OrderItemOrders = new HashSet<>();


    public Order(Long id, Order_Status order_status, Client id_client, LocalDateTime orderExchangePeriod, LocalDateTime orderDate, LocalDateTime orderArrivalDate, Point orderCurrentPosition, Set<Order_Item> orderItemOrders) {
        this.id = id;
        this.order_status = order_status;
        this.id_client = id_client;
        this.orderExchangePeriod = orderExchangePeriod;
        this.orderDate = orderDate;
        this.orderArrivalDate = orderArrivalDate;
        this.orderCurrentPosition = orderCurrentPosition;
        OrderItemOrders = orderItemOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order_Status getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Order_Status order_status) {
        this.order_status = order_status;
    }

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
    }

    public LocalDateTime getOrderExchangePeriod() {
        return orderExchangePeriod;
    }

    public void setOrderExchangePeriod(LocalDateTime orderExchangePeriod) {
        this.orderExchangePeriod = orderExchangePeriod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderArrivalDate() {
        return orderArrivalDate;
    }

    public void setOrderArrivalDate(LocalDateTime orderArrivalDate) {
        this.orderArrivalDate = orderArrivalDate;
    }

    public Point getOrderCurrentPosition() {
        return orderCurrentPosition;
    }

    public void setOrderCurrentPosition(Point orderCurrentPosition) {
        this.orderCurrentPosition = orderCurrentPosition;
    }

    public Set<Order_Item> getOrderItemOrders() {
        return OrderItemOrders;
    }

    public void setOrderItemOrders(Set<Order_Item> orderItemOrders) {
        OrderItemOrders = orderItemOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && order_status == order.order_status && Objects.equals(id_client, order.id_client) && Objects.equals(orderExchangePeriod, order.orderExchangePeriod) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderArrivalDate, order.orderArrivalDate) && Objects.equals(orderCurrentPosition, order.orderCurrentPosition) && Objects.equals(OrderItemOrders, order.OrderItemOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order_status, id_client, orderExchangePeriod, orderDate, orderArrivalDate, orderCurrentPosition, OrderItemOrders);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_status=" + order_status +
                ", id_client=" + id_client +
                ", orderExchangePeriod=" + orderExchangePeriod +
                ", orderDate=" + orderDate +
                ", orderArrivalDate=" + orderArrivalDate +
                ", orderCurrentPosition=" + orderCurrentPosition +
                ", OrderItemOrders=" + OrderItemOrders +
                '}';
    }

    public  void addOrderItem(Order_Item orderItem){
        OrderItemOrders.add(orderItem);
    }
    public  void removeOrderItem(Order_Item orderItem){OrderItemOrders.remove(orderItem);}
}
