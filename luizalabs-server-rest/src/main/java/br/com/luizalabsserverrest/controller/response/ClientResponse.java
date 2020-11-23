package br.com.luizalabsserverrest.controller.response;

import br.com.luizalabsserverrest.model.entity.ClientEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ClientResponse {

    private Long id;
    private String name;
    private String email;

    public static ClientResponse convertToResponse(ClientEntity clientEntity)
    {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(clientEntity.getId());
        clientResponse.setEmail(clientEntity.getEmail());
        clientResponse.setName(clientEntity.getName());

        return clientResponse;

    }

    public static List<ClientResponse> convertToResponseList(List<ClientEntity> clientEntityList)
    {
        return clientEntityList.stream()
                .map(ClientResponse::convertToResponse)
                .collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
