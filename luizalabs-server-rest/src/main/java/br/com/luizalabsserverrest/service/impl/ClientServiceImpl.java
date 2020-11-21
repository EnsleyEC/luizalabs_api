package br.com.luizalabsserverrest.service.impl;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.repository.ClientRepository;
import br.com.luizalabsserverrest.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements GenericService<ClientEntity> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<ClientEntity> findAll(Pageable pageable) {

        return clientRepository.findAll(pageable);
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public ClientEntity save(ClientEntity type) {
        return clientRepository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
