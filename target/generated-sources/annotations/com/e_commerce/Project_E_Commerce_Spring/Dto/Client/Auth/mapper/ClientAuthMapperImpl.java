package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.mapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.Auth.ClientAuthRequestRegister;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-20T23:03:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ClientAuthMapperImpl implements ClientAuthMapper {

    @Override
    public Client registerRequestToEntity(ClientAuthRequestRegister clientAuthRequestRegister) {
        if ( clientAuthRequestRegister == null ) {
            return null;
        }

        Client client = new Client();

        client.setClientName( clientAuthRequestRegister.getClientName() );
        client.setEmailClient( clientAuthRequestRegister.getEmailClient() );
        client.setPassword( clientAuthRequestRegister.getPassword() );
        client.setNumber( clientAuthRequestRegister.getNumber() );
        client.setClientAddress( clientAuthRequestRegister.getClientAddress() );

        return client;
    }
}
