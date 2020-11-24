package br.com.luizalabsserverrest.controller.request;

import br.com.luizalabsserverrest.model.entity.ClientEntity;

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
