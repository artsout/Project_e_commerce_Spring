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
    private long id;

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



    public  void addOrderItem(Order_Item orderItem){
        OrderItemOrders.add(orderItem);
    }
    public  void removeOrderItem(Order_Item orderItem){
        OrderItemOrders.remove(orderItem);
    }
}
