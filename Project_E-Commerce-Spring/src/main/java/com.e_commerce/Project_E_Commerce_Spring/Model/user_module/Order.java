package com.e_commerce.Project_E_Commerce_Spring.Model.user_module;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Payment;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Payment> payments = new HashSet<>();

    public  void addOrderItem(Order_Item orderItem){
        OrderItemOrders.add(orderItem);
    }
    public  void removeOrderItem(Order_Item orderItem){OrderItemOrders.remove(orderItem);}
}
