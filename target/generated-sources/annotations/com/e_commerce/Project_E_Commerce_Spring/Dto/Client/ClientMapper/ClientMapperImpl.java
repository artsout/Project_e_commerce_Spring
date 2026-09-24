package com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientMapper;

import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.ClientRequest;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.responses.ClientAdminResponse;
import com.e_commerce.Project_E_Commerce_Spring.Dto.Client.responses.ClientUserResponse;
import com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-23T14:37:25-0300",
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
    public ClientAdminResponse toDtoResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientAdminResponse clientAdminResponse = new ClientAdminResponse();

        clientAdminResponse.setId( client.getId() );
        clientAdminResponse.setClientCreationDate( client.getClientCreationDate() );
        clientAdminResponse.setClientNotificacaoCount( client.getClientNotificacaoCount() );
        clientAdminResponse.setClientPedidoCount( client.getClientPedidoCount() );
        clientAdminResponse.setClientName( client.getClientName() );
        clientAdminResponse.setNumber( client.getNumber() );

        return clientAdminResponse;
    }

    @Override
    public ClientUserResponse toDtoUserResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientUserResponse clientUserResponse = new ClientUserResponse();

        clientUserResponse.setId( client.getId() );
        clientUserResponse.setClientCreationDate( client.getClientCreationDate() );
        clientUserResponse.setFollowsCount( client.getFollowsCount() );
        clientUserResponse.setClientName( client.getClientName() );

        return clientUserResponse;
    }
}
