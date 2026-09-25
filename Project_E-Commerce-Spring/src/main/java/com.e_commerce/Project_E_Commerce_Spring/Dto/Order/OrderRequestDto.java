package com.e_commerce.Project_E_Commerce_Spring.Dto.Order;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Order_Item.Order_ItemDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.PaymentRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Order_Item;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Payment;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {




    @NotNull
    private LocalDateTime orderExchangePeriod;

    @NotNull
    private LocalDateTime orderDate;

    @Null
    private LocalDateTime orderArrivalDate;

    @NotNull
    private CurrentPosition orderCurrentPosition;


    @NotNull
    private Set<Order_ItemDto> OrderItemOrders= new HashSet<>();

    @NotNull
    private Set<PaymentRequestDto> payments=new HashSet<>();
}
