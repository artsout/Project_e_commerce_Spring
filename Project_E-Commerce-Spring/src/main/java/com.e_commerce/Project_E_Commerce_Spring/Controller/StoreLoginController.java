package com.e_commerce.Project_E_Commerce_Spring.Controller;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Store.Auth.StoreAuthResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Store;
import com.e_commerce.Project_E_Commerce_Spring.Repository.product_module.StoreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreLoginController {

    private final JwtEncoder jwtEncoder;
    private final StoreRepository storeRepository;
    private  final Argon2PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<StoreAuthResponse> login(@Valid @RequestBody StoreAuthRequest storeAuthRequest){
       Store store = storeRepository.findByEmail(storeAuthRequest.getEmail());
        if (store == null || !store.isLoginCorrect(storeAuthRequest,passwordEncoder)){
            throw  new BadCredentialsException("email or password is invalid");
        }
        var expiresIn = 3600L;

        var scope = store.getRoles()
                .stream()
                .map(storeRole-> storeRole.getTypeOfStoreRoles().name())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(store.getId().toString())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValues = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new StoreAuthResponse(jwtValues,expiresIn));
    }
}
