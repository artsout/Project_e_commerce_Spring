package com.e_commerce.Project_E_Commerce_Spring.service;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Mapper.StoreDtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.StoreUpdateDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private  final StoreDtoMapper storeDtoMapper;
    public Store findById(UUID storeId){
        if(storeId== null){
            throw  new IllegalArgumentException("Paramenter invalid");
        }
       Store store = storeRepository.findById(storeId).orElseThrow(() -> new NoSuchElementException("Store not found with this id"));
       return store;
    }


    public  void updateStore(UUID storeId , StoreUpdateDto storeUpdateDto){
        if(storeId== null || storeUpdateDto==null){
            throw  new IllegalArgumentException("Paramenter invalid");
        }

        Store store = findById(storeId);

        if (storeUpdateDto.getStoreAddress() != null) {
            store.setStoreAddress(storeUpdateDto.getStoreAddress());
        }
        if (storeUpdateDto.getCnpj() != null) {
            store.setCnpj(storeUpdateDto.getCnpj());
        }
        if (storeUpdateDto.getEmail() != null) {
            store.setEmail(storeUpdateDto.getEmail());
        }

        Store result = storeRepository.save(store);
    }
    public  Store findByEmail(String email){
        if(email == null){
            throw  new IllegalArgumentException("Paramenter invalid");
        }
        Store store = storeRepository.findByEmail(email);

        return  store;
    }
    public  Store findByCnpj(String cnpj){
        if(cnpj == null){
            throw  new IllegalArgumentException("Paramenter invalid");
        }

        Store store = storeRepository.findByCnpj(cnpj);

        return  store;
    }
    public List<Store> findByStoreAddress(Address address){
        if(address == null){
            throw  new IllegalArgumentException("Paramenter invalid");
        }
        List<Store> store = storeRepository.findByStoreAddress(address.getRua(), address.getCidade(), address.getCep());
        if(store.isEmpty()){
            throw  new NoSuchElementException("No stores in this Address");
        }
        return  store;
    }
}
