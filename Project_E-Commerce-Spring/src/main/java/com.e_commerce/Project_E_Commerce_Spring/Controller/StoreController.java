package com.e_commerce.Project_E_Commerce_Spring.Controller;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper.StoreDtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

   private final StoreService storeService;
   private  final StoreDtoMapper storeDtoMapper;


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
