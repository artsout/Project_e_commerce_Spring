package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


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
    private Order_Status orderStatus;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


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
    @Embedded
    @Column(nullable = false , columnDefinition = "POINT")
   private CurrentPosition orderCurrentPosition;


    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Order_Item> OrderItemOrders = new HashSet<>();


    public Order(Long id, Order_Status orderStatus, Client client, LocalDateTime orderExchangePeriod, LocalDateTime orderDate, LocalDateTime orderArrivalDate, CurrentPosition orderCurrentPosition, Set<Order_Item> orderItemOrders) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.client = client;
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

    public Order_Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Order_Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public CurrentPosition getOrderCurrentPosition() {
        return orderCurrentPosition;
    }

    public void setOrderCurrentPosition(CurrentPosition orderCurrentPosition) {
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
        return id == order.id && orderStatus == order.orderStatus && Objects.equals(client, order.client) && Objects.equals(orderExchangePeriod, order.orderExchangePeriod) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderArrivalDate, order.orderArrivalDate) && Objects.equals(orderCurrentPosition, order.orderCurrentPosition) && Objects.equals(OrderItemOrders, order.OrderItemOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus, client, orderExchangePeriod, orderDate, orderArrivalDate, orderCurrentPosition, OrderItemOrders);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_status=" + orderStatus +
                ", id_client=" + client +
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
