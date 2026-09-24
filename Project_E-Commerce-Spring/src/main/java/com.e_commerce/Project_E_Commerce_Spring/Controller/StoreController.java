package com.e_commerce.Project_E_Commerce_Spring.Controller;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthRegisterRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper.StoreDtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.response.StoreDtoPublicResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.StoreRole;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Role.TypeOfStoreRoles;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRoleRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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


      StoreRole role = storeRoleRepository.findByTypeOfStoreRoles(TypeOfStoreRoles.OWNER).orElseThrow(() -> new NoSuchElementException("Not found this role"));

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
   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
   public ResponseEntity<StoreDto> updateStore(@PathVariable UUID storeId ,@Valid @RequestBody StoreUpdateDto dto){
        storeService.updateStore(storeId,dto);
        Store store = storeService.findById(storeId);
       StoreDto storeDto = storeDtoMapper.toDto(store);
        return  ResponseEntity.ok(storeDto);
   }

    @PutMapping("/update/me")
    public ResponseEntity<StoreDto> updateStore(Authentication authentication, @Valid @RequestBody StoreUpdateDto dto){
      String storeId = authentication.getName();

       storeService.updateStore(UUID.fromString(storeId),dto);

        Store store = storeService.findById(UUID.fromString(storeId));

        StoreDto storeDto = storeDtoMapper.toDto(store);
        return  ResponseEntity.ok(storeDto);
    }

    @GetMapping("/{storeId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<StoreDto> findById(@PathVariable UUID storeId){
        Store store =  storeService.findById(storeId);
        StoreDto storeDto  = storeDtoMapper.toDto(store);

        return ResponseEntity.ok(storeDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StoreDtoPublicResponse> findByEmail(@PathVariable String email){
        Store store =  storeService.findByEmail(email);
        StoreDtoPublicResponse storeDtoPublicResponse  = storeDtoMapper.toPublicDto(store);

        return ResponseEntity.ok(storeDtoPublicResponse);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<StoreDtoPublicResponse> findByCnpj(@PathVariable String cnpj){
        Store store =  storeService.findByCnpj(cnpj);
        StoreDtoPublicResponse storeDtoPublicResponse  = storeDtoMapper.toPublicDto(store);

        return ResponseEntity.ok(storeDtoPublicResponse);
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<StoreDtoPublicResponse>> findByAddress(@RequestBody Address address){
        List<Store> stores =  storeService.findByStoreAddress(address);
        List<StoreDtoPublicResponse> result  = stores.stream()
                .map(storeDtoMapper::toPublicDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


    @PatchMapping("/patch/{storeId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<StoreDto> patch(@PathVariable UUID storeId , @Valid @RequestBody StoreUpdateDto storeUpdateDto){
       storeService.updateStore(storeId,storeUpdateDto);

       Store store= storeService.findById(storeId);

       StoreDto storeDto = storeDtoMapper.toDto(store);

      return  ResponseEntity.ok(storeDto);
    }

    @PatchMapping("/patch/me")
    public ResponseEntity<StoreDto> patch(Authentication authentication, @Valid @RequestBody StoreUpdateDto storeUpdateDto){
        String storeId = authentication.getName();
        storeService.updateStore(UUID.fromString(storeId),storeUpdateDto);

        Store store= storeService.findById(UUID.fromString(storeId));

        StoreDto storeDto = storeDtoMapper.toDto(store);

        return  ResponseEntity.ok(storeDto);
    }


    @DeleteMapping("/delete/{storeId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID storeId){
       Store store = storeService.findById(storeId);
        storeRepository.delete(store);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/delete/me")
    public ResponseEntity<Void> delete(Authentication authentication){

       String storeId = authentication.getName();

        Store store = storeService.findById(UUID.fromString(storeId));
        storeRepository.delete(store);
        return ResponseEntity.noContent().build();
    }
}
