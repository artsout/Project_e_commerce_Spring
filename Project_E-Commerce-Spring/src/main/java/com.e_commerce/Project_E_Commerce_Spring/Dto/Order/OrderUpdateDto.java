package com.e_commerce.Project_E_Commerce_Spring.Dto.Order;


import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position.CurrentPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto {


    private LocalDateTime orderExchangePeriod;

    private LocalDateTime orderArrivalDate;

    private CurrentPosition orderCurrentPosition;
}
