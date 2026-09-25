package com.e_commerce.Project_E_Commerce_Spring.Dto.Order_Item;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_ItemDto {

    @NotNull
    private Integer orderItemQuantity;
    //mapping
    @NotNull
    private Long productId;
}
