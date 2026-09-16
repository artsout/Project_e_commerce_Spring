package com.e_commerce.Project_E_Commerce_Spring.Dto.follow;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow_Store_Dto {

    @Null
    private Long id;
    private LocalDateTime followStoreDate;

    @Null
    private UUID clientId;
    @Null
    private UUID storeId;
}
