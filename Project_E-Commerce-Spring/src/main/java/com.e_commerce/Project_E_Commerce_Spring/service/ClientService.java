package com.e_commerce.Project_E_Commerce_Spring.service;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientUpdatedRequest;
import com.e_commerce.Project_E_Commerce_Spring.Model.aux_Adress_model.Address;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Product_Rating;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.Product_RatingRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.Follow_StoreRepository;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.NotificationRepository;
import com.e_commerce.Project_E_Commerce_Spring.service.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private  final NotificationRepository notificationRepository;
    private final Follow_StoreService followStoreService;
    private final Product_RatingRepository productRatingRepository;
    private final Follow_StoreRepository followStoreRepository;

    public Client findById(UUID clientId){
        if(clientId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
         Client client = clientRepository.findById(clientId)
              .orElseThrow(() -> new NoSuchElementException("Client with ID " + clientId + " not found"));
        return  client;
    }

    public void update(Client client , ClientUpdatedRequest clientUpdatedRequest ){
        if(client == null || clientUpdatedRequest==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        if(findById(client.getId()) == null ){
            throw  new ResourceNotFoundException("This client do not exist");
        }

        if(clientUpdatedRequest.getClientAddress()!=null ){
            client.setClientAddress(clientUpdatedRequest.getClientAddress());
        }
        if(clientUpdatedRequest.getEmailClient() != null && !clientUpdatedRequest.getEmailClient().isEmpty()){
            client.setEmailClient(clientUpdatedRequest.getEmailClient());
        }
        if(clientUpdatedRequest.getClientName() != null && !clientUpdatedRequest.getClientName().isEmpty()) {
            client.setClientName(clientUpdatedRequest.getClientName());
        }
        if(clientUpdatedRequest.getNumber() != null && !clientUpdatedRequest.getNumber().isEmpty()){
            client.setNumber(clientUpdatedRequest.getNumber());
        }
        if(clientUpdatedRequest.getPassword() != null && !clientUpdatedRequest.getPassword().isEmpty()){
            client.setPassword(clientUpdatedRequest.getPassword());
        }

        clientRepository.save(client);
    }


    public  Client findByNumber(String number){
        if(number == null || number.isEmpty()){
            throw  new IllegalArgumentException("You have to put some number");
        }
         final String REGEX_TELEFONE = "^\\+\\d{2}\\d{2}\\d{5}-\\d{4}$";

        String telefoneLimpo = number.strip();
        if(!number.matches(REGEX_TELEFONE)){
            throw  new IllegalArgumentException("This number is not valid");
        }
        return clientRepository.findByNumber(number);
    }

    public  Client findByEmail( String email){
        if(email == null || email.isEmpty()){
            throw  new IllegalArgumentException("You have to put some number");
        }
        return clientRepository.findByEmailClient(email);
    }

    public List<Client> findByNameContaining(String clientName){
        if(clientName == null || clientName.isEmpty()){
            throw  new IllegalArgumentException("You have to put some number");
        }
      return   clientRepository.findByClientNameContainingIgnoreCase(clientName);
    }


    public  List<Product_Rating> getRatingsByClientId(UUID clientId){
        if(clientId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }

        List<Product_Rating> ratings =  productRatingRepository.findByClientId(clientId);
        if(ratings.isEmpty()){
            throw new NoSuchElementException("Notifications not found");
        }
        return  ratings;
    }
    public  List<Follow_Store> getFollowsByClientId(UUID clientId , LocalDateTime inicio , LocalDateTime fim){
        if(clientId == null ){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        List<Follow_Store> follows;
        if(fim!=null){
            if(inicio.isAfter(fim)){
                throw  new IllegalArgumentException("This dates are not valid");
            }
            follows =  followStoreRepository.findByClientIdAndFollowStoreDateBetween(clientId,inicio,fim);
            if(follows.isEmpty()){
                throw new NoSuchElementException("Notifications not found");
            }

            return follows;
        }else {
            follows = followStoreRepository.findByClientId(clientId);
            return follows;
        }
    }

    public  List<Client> findByClientAddress(Address address){
        if(address==null){
            throw  new IllegalArgumentException("Parameter cant be null");
        }
        List<Client>  clients =   clientRepository.findByClientAddress(address.getRua(),address.getCidade(),address.getCep());
        if(clients.isEmpty()){
            throw new NoSuchElementException("Notifications not found");
        }
        return  clients;
    }

}
