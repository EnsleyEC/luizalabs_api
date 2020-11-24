package br.com.luizalabsserverrest.service;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import br.com.luizalabsserverrest.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountEntity> accountEntity = accountService.findByUsername(username);
        if (!accountEntity.isPresent()) {
            throw new UsernameNotFoundException("Usuário não existe: " + username);
        }

        // third parameter is about roles, we can create here
        return new User(accountEntity.get().getUsername(), accountEntity.get().getPassword(),new ArrayList<>());
    }

}