package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.repository.ClientRepository;
import br.com.luizalabsserverrest.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = Constants.ROUTE_CLIENT)
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public List<ClientEntity> findAll() {

        return repository.findAll();
    }

}
