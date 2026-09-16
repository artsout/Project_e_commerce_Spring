package com.e_commerce.Project_E_Commerce_Spring.Controller;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper.ClientMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;

import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.service.Follow_StoreService;
import com.e_commerce.Project_E_Commerce_Spring.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/store-follow")
@RequiredArgsConstructor
public class StoreFollowController {

    private final Follow_StoreService followStoreService;
    private  final ClientMapper clientMapper;
    private final Follow_Store_DtoMapper followStoreDtoMapper;

    @PostMapping("/client/{clientId}/follow/{storeId}") // 💡 Adicionada a barra e corrigido para {clientId}
    public ResponseEntity<Follow_Store_Dto> followStore(@PathVariable UUID clientId,
                                                        @PathVariable UUID storeId){
        Follow_Store follow_store = followStoreService.follow(clientId,storeId);
        Follow_Store_Dto response = followStoreDtoMapper.toDto(follow_store);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{clientId}/unfollow/{storeId}")
    public ResponseEntity<Follow_Store_Dto> unFollowStore(@PathVariable UUID clientId,
                                                      @PathVariable UUID storeId){
        Follow_Store follow_store = followStoreService.unFollow(clientId,storeId);
        Follow_Store_Dto response = followStoreDtoMapper.toDto(follow_store);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{storeId}/followers")
    @Transactional
    public  ResponseEntity<List<Follow_Store_Dto>> getFollows(@PathVariable UUID storeId){
        List<Follow_Store> followStores = followStoreService.getFollowsByStoreId(storeId);
        List<Follow_Store_Dto> result = followStores.stream()
                .map(followStoreDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
