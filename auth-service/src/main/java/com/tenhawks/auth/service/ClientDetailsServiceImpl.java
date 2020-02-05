package com.tenhawks.auth.service;

import com.tenhawks.auth.domain.Client;
import com.tenhawks.auth.repository.ClientRepository;
import com.tenhawks.auth.security.CassandraClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.UUID;

/**
 * @author Mukhtiar Ahmed
 */
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private ClientRepository clientRepository;

    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId){
        Client client =  clientRepository.findByClientId(UUID.fromString(clientId));
        if(client != null) {
            return new CassandraClientDetails(client);
        } else {
            return null;
        }
    }
}
