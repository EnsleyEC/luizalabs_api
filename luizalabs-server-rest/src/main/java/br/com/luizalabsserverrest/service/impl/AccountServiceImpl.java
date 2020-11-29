package br.com.luizalabsserverrest.service.impl;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import br.com.luizalabsserverrest.repository.AccountRepository;
import br.com.luizalabsserverrest.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements GenericService<AccountEntity> {

    @Autowired
    private AccountRepository repository;

    @Override
    public List<AccountEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<AccountEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<AccountEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AccountEntity> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public AccountEntity save(AccountEntity accountEntity) {

        accountEntity.setPassword(BCrypt.hashpw(accountEntity.getPassword(), BCrypt.gensalt(10)));

        return repository.save(accountEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<AccountEntity> findByUsername(String username)
    {
        return repository.findByUsername(username);
    }

    public boolean existsByUsername(String username)
    {
        return repository.existsByUsername(username);
    }

}
