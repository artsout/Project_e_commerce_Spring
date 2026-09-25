package com.e_commerce.Project_E_Commerce_Spring.Controller.Client;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthResponse;
import com.e_commerce.Project_E_Commerce_Spring.Repository.user_module.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientLoginController {

    private final JwtEncoder jwtEncoder;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ClientAuthResponse> login(@RequestBody ClientAuthRequest clientAuthRequest){
       var client = clientRepository.findByEmailClient(clientAuthRequest.getEmailClient());
        if (client == null || !client.isLoginCorrect(clientAuthRequest,passwordEncoder)){
            throw  new BadCredentialsException("email or password is invalid");
        }
        var expiresIn = 3600L;

        var scope = client.getRoles()
                .stream()
                .map(clientRole-> clientRole.getTypeOfClientRole().name())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(client.getId().toString())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValues = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return  ResponseEntity.ok(new ClientAuthResponse(jwtValues,expiresIn));
    }
}
