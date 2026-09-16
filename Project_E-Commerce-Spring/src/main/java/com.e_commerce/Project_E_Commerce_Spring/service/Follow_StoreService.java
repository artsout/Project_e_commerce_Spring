package com.e_commerce.Project_E_Commerce_Spring.service;

import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.Follow_StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Follow_StoreService {

    private final StoreRepository storeRepository;
    private final Follow_StoreRepository followStoreRepository;
    private final ClientRepository clientRepository;


    @Transactional
    public Follow_Store follow(UUID clientId, UUID storeId){
        if(clientId == null || storeId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        boolean jaSegue = followStoreRepository.existsByClientIdAndStoreId(clientId, storeId);
        if(jaSegue){
            throw new IllegalStateException("Client already follow this store");
        }
        Client client = clientRepository.findById(clientId).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Follow_Store follow_store = new Follow_Store();
        follow_store.setStore(store);
        follow_store.setFollowStoreDate(LocalDateTime.now());
        follow_store.setClient(client);
        store.setFollowCount(store.getFollowCount()+1);
        client.setFollowsCount(client.getFollowsCount()+1);

        //Salva alteraçao do count
        storeRepository.save(store);
        clientRepository.save(client);

        Follow_Store savedFollow = followStoreRepository.save(follow_store);

        return savedFollow;
    }

    public Follow_Store unFollow(UUID clientId,UUID storeId){
        if(clientId == null || storeId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        if(!followStoreRepository.existsByClientIdAndStoreId(clientId, storeId)){
            throw new IllegalStateException("They not even following each other");
        }
        Client client = clientRepository.findById(clientId).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Follow_Store deleteFollow = followStoreRepository.findByClientIdAndStoreId(clientId,storeId);

        followStoreRepository.delete(deleteFollow);

        store.setFollowCount(store.getFollowCount() - 1);
        client.setFollowsCount(client.getFollowsCount() - 1);

        storeRepository.save(store);
        clientRepository.save(client);

        return deleteFollow;
    }

    public List<Follow_Store> getFollowsByStoreId(UUID storeId){
        if(storeId==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }

      List<Follow_Store> followStores =  followStoreRepository.findByStoreId(storeId);
      if (followStores.isEmpty()){
          throw new NoSuchElementException("Client not found");
      }

        return followStores;
    }
}
