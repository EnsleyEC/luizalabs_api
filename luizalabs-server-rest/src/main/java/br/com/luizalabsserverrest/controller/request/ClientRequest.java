package br.com.luizalabsserverrest.controller.request;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.service.GenericService;

import java.util.ArrayList;
import java.util.List;

public class ClientRequest {

    private String name;
    private String email;

    public static ClientEntity convertToEntity(ClientRequest clientRequest)
    {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setName(clientRequest.getName());

        return clientEntity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
