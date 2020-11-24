package br.com.luizalabsserverrest.controller.request;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountRequest {

    private String username;
    private String password;

    public static AccountEntity convertToEntity(AccountRequest accountRequest)
    {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(accountRequest.getUsername());
        accountEntity.setPassword(accountRequest.getPassword());

        return accountEntity;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
