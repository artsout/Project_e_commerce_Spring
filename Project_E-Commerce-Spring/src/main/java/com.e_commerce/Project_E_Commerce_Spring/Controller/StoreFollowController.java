package com.e_commerce.Project_E_Commerce_Spring.Controller;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper.ClientMapper;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_Dto;
import com.e_commerce.Project_E_Commerce_Spring.Dto.follow.Follow_Store_DtoMapper.Follow_Store_DtoMapper;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Follow_Store;
import com.e_commerce.Project_E_Commerce_Spring.service.Follow_StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @PostMapping("/follow/{storeId}")
    @PreAuthorize("hasAnyRole('CLIENT', 'USER', 'ADMIN')")
    public ResponseEntity<Follow_Store_Dto> followStore(@AuthenticationPrincipal Jwt jwt,
                                                        @PathVariable UUID storeId){
        UUID clientId = UUID.fromString(jwt.getSubject());
        Follow_Store follow_store = followStoreService.follow(clientId,storeId);
        Follow_Store_Dto response = followStoreDtoMapper.toDto(follow_store);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/unfollow/{storeId}")
    @PreAuthorize("hasAnyRole('CLIENT', 'USER', 'ADMIN')")
    public ResponseEntity<Follow_Store_Dto> unFollowStore(@AuthenticationPrincipal Jwt jwt,
                                                          @PathVariable UUID storeId){

        UUID clientId = UUID.fromString(jwt.getSubject());
        Follow_Store follow_store = followStoreService.unFollow(clientId,storeId);
        Follow_Store_Dto response = followStoreDtoMapper.toDto(follow_store);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{storeId}/followers")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public  ResponseEntity<List<Follow_Store_Dto>> getStoreFollowers(@PathVariable UUID storeId){
        List<Follow_Store> followStores = followStoreService.getFollowsByStoreId(storeId);
        List<Follow_Store_Dto> result = followStores.stream()
                .map(followStoreDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{clientId}/following")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Follow_Store_Dto>> getClientFollowing(@PathVariable UUID clientId){
        List<Follow_Store> followStores = followStoreService.findByClientId(clientId);

        List<Follow_Store_Dto> result = followStores.stream()
                .map(followStoreDtoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}