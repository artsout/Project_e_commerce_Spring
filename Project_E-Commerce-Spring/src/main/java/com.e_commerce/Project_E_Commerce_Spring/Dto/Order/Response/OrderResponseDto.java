package com.e_commerce.Project_E_Commerce_Spring.Dto.Order.Response;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Order_Enum.Order_Status;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private UUID clientId;
    private LocalDateTime orderDate;
    private LocalDateTime orderArrivalDate;
    private LocalDateTime orderExchangePeriod;
    private Order_Status orderStatus;
    private CurrentPosition orderCurrentPosition;
}