package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = Constants.ROUTE_CLIENT)
public class ClientController {

    @Autowired
    private GenericService<ClientEntity> service;

    @GetMapping
    public List<?> findAll() {

        return service.findAll();
    }

    @GetMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }

    @PostMapping(path = Constants.ROUTE_SAVE)
    public ClientEntity create(@RequestBody ClientEntity client) {
        return service.save(client);
    }

    @PutMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<ClientEntity> update(@PathVariable("id") Long id, @RequestBody ClientEntity newClient) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        ClientEntity clientSave = client.get();
        clientSave.setEmail(newClient.getEmail());
        clientSave.setName(newClient.getName());

        return ResponseEntity.ok(service.save(clientSave));
    }

    @DeleteMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        if(!service.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
