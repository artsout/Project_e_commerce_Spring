package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-14T19:39:28-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        Client client = new Client();

        client.setClientName( clientRequest.getClientName() );
        client.setEmailClient( clientRequest.getEmailClient() );
        client.setNumber( clientRequest.getNumber() );
        client.setClientAddress( clientRequest.getClientAddress() );

        return client;
    }

    @Override
    public ClientRequest toDtoRequest(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientRequest clientRequest = new ClientRequest();

        clientRequest.setClientName( client.getClientName() );
        clientRequest.setEmailClient( client.getEmailClient() );
        clientRequest.setNumber( client.getNumber() );
        clientRequest.setClientAddress( client.getClientAddress() );

        return clientRequest;
    }

    @Override
    public ClientResponse toDtoResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId( client.getId() );
        clientResponse.setClientCreationDate( client.getClientCreationDate() );
        clientResponse.setClientNotificacaoCount( client.getClientNotificacaoCount() );
        clientResponse.setClientPedidoCount( client.getClientPedidoCount() );
        clientResponse.setClientName( client.getClientName() );
        clientResponse.setEmailClient( client.getEmailClient() );
        clientResponse.setPassword( client.getPassword() );
        clientResponse.setNumber( client.getNumber() );
        clientResponse.setClientAddress( client.getClientAddress() );

        return clientResponse;
    }
}
