package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthRequestRegister;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.mapper.ClientAuthMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper.ClientMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientUpdatedRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.Mapper.ProductRatingMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.ProductRating.ProductRatingDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.ClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.TypeOfClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRoleRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;
    private  final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final Argon2PasswordEncoder passwordEncoder;
    private  final Follow_Store_DtoMapper followStoreDtoMapper;
    private final ProductRatingMapper productRatingMapper;
    private final ClientAuthMapper clientAuthMapper;
    private  final ClientRoleRepository clientRoleRepository;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody ClientAuthRequestRegister clientAuthRequestRegister){

        Optional<ClientRole> role = clientRoleRepository.findByTypeOfClientRole(TypeOfClientRole.USER);

        if (clientService.findByEmail(clientAuthRequestRegister.getEmailClient()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This store already exist");
        }

        Client clientSaved = clientAuthMapper.registerRequestToEntity(clientAuthRequestRegister);

        String password = passwordEncoder.encode(clientAuthRequestRegister.getPassword());
        clientSaved.setPassword(password);

        ClientRole clientRole = role.orElseThrow(() -> new NoSuchElementException("A role USER do not exist in db"));
        clientSaved.setRoles(Set.of(clientRole));
        clientSaved.setClientCreationDate(LocalDateTime.now());

        clientRepository.save(clientSaved);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/search/address")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<ClientResponse>> findByAddress(@RequestBody Address address){
        List<Client> clients =  clientService.findByClientAddress(address);
        List<ClientResponse> result  = clients.stream()
                .map(clientMapper::toDtoResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{clientId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
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
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ClientResponse> getClientByNumber(@PathVariable String number) {
        Client client = clientService.findByNumber(number);
        ClientResponse response = clientMapper.toDtoResponse(client);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
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

    /**
    @PatchMapping


    @DeleteMapping
    **/

}
