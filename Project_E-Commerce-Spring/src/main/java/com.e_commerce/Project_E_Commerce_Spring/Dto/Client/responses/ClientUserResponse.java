package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUserResponse {
    private UUID id;

    private LocalDateTime clientCreationDate;

    private Integer followsCount;

    private String clientName;
}
