package com.e_commerce.Project_E_Commerce_Spring.Controller;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthRegisterRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper.StoreDtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.StoreRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.TypeOfStoreRoles;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Role.ClientRole;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRoleRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

   private final StoreService storeService;
   private  final StoreDtoMapper storeDtoMapper;
    private final StoreRepository storeRepository;
    private final Argon2PasswordEncoder passwordEncoder;
    private  final StoreRoleRepository storeRoleRepository;

   @PostMapping("/register")
   public ResponseEntity<Void> register(@Valid @RequestBody StoreAuthRegisterRequest storeAuthRegisterRequest){


        StoreRole role = storeRoleRepository.findByTypeOfStoreRoles(TypeOfStoreRoles.OWNER);

      Store store =storeRepository.findByCnpj(storeAuthRegisterRequest.getCnpj());
      if(store != null ){
          throw new ResponseStatusException(HttpStatus.CONFLICT, "This store already exist");
      }


      Store storeSaved = new Store();
      storeSaved.setEmail(storeAuthRegisterRequest.getEmail());
      storeSaved.setPassword(passwordEncoder.encode(storeAuthRegisterRequest.getPassword()));
      storeSaved.setStoreAddress(storeAuthRegisterRequest.getStoreAddress());
      storeSaved.setCnpj(storeAuthRegisterRequest.getCnpj());


      storeSaved.setRoles(Set.of(role));

      storeRepository.save(storeSaved);

     return ResponseEntity.status(HttpStatus.CREATED).build();
   }

   @PutMapping("/update/{storeId}")
   public ResponseEntity<Void> updateStore(@PathVariable UUID storeId , @RequestBody StoreUpdateDto dto){
        storeService.updateStore(storeId,dto);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
   }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDto> findById(@PathVariable UUID storeId){
        Store store =  storeService.findById(storeId);
        StoreDto storeDto  = storeDtoMapper.toDto(store);

        return ResponseEntity.ok(storeDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StoreDto> findByEmail(@PathVariable String email){
        Store store =  storeService.findByEmail(email);
        StoreDto storeDto  = storeDtoMapper.toDto(store);

        return ResponseEntity.ok(storeDto);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<StoreDto> findByCnpj(@PathVariable String cnpj){
        Store store =  storeService.findByCnpj(cnpj);
        StoreDto storeDto  = storeDtoMapper.toDto(store);

        return ResponseEntity.ok(storeDto);
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<StoreDto>> findByAddress(@RequestBody Address address){
        List<Store> stores =  storeService.findByStoreAddress(address);
        List<StoreDto> result  = stores.stream()
                .map(storeDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
