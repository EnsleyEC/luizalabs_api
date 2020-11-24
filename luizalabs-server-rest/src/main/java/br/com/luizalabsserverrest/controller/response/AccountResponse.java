package br.com.luizalabsserverrest.controller.response;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import br.com.luizalabsserverrest.model.entity.ClientEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AccountResponse {

    private Long id;
    private String username;

    public static AccountResponse convertToResponse(AccountEntity accountEntity)
    {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(accountEntity.getId());
        accountResponse.setUsername(accountEntity.getUsername());

        return accountResponse;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
