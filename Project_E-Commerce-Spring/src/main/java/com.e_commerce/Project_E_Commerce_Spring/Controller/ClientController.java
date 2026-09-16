package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper.ClientMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientUpdatedRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Notification.NotificationMapper.NotificationMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.Mapper.ProductRatingMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.ProductRatingDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.NotificationType;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.NotificationEnum.Notification_Class;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.ClientService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;
    private  final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final NotificationMapper notificationMapper;
    private  final Follow_Store_DtoMapper followStoreDtoMapper;
    private final ProductRatingMapper productRatingMapper;




    @GetMapping("/search/address")
    public ResponseEntity<List<ClientResponse>> findByAddress(@RequestBody Address address){
        List<Client> clients =  clientService.findByClientAddress(address);
        List<ClientResponse> result  = clients.stream()
                .map(clientMapper::toDtoResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponse> findById( @PathVariable UUID clientId){
       Client client =  clientService.findById(clientId);

        ClientResponse response = clientMapper.toDtoResponse(client);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<ClientResponse> updateClientInformation(@PathVariable UUID clientId,
                                                   @Valid @RequestBody ClientUpdatedRequest clientUpdatedRequest){
        Client client =  clientService.findById(clientId);

        clientService.update(client , clientUpdatedRequest);

        ClientResponse response = clientMapper.toDtoResponse(client);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/number/{number}")
    public ResponseEntity<ClientResponse> getClientByNumber(@PathVariable String number) {
        Client client = clientService.findByNumber(number);
        ClientResponse response = clientMapper.toDtoResponse(client);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ClientResponse> getClientByEmail(@RequestParam String email) {
        Client client = clientService.findByEmail(email);
        ClientResponse response = clientMapper.toDtoResponse(client);
        return ResponseEntity.ok (response);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<ClientResponse>> getClientsByName(@RequestParam String name) {
        List<Client> clients = clientService.findByNameContaining(name);
        List<ClientResponse> response = clients.stream()
                .map(clientMapper::toDtoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}/ratings")
    public ResponseEntity<List<ProductRatingDto>> getRatingsByClientId(@PathVariable UUID clientId){
          List<Product_Rating> ratings = clientService.getRatingsByClientId(clientId);

            List<ProductRatingDto> result = ratings.stream()
                    .map(productRatingMapper::toDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(result);
    }

    @GetMapping("/{clientId}/follows")
    public  ResponseEntity<List<Follow_Store_Dto>> getFollows(@PathVariable UUID clientId,
                                                                @RequestParam(required = false) LocalDateTime inicio,
                                                                    @RequestParam(required = false) LocalDateTime fim){

        if (fim != null && inicio == null) {
            inicio = LocalDateTime.now();
        }

        List<Follow_Store> follows = clientService.getFollowsByClientId(clientId,inicio,fim);
        List<Follow_Store_Dto> result = follows.stream().map(followStoreDtoMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(result);
    }
}
