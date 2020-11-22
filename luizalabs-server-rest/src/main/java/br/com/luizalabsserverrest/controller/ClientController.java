package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Client")
@RequestMapping(path = Constants.ROUTE_CLIENT)
public class ClientController {

    @Autowired
    private GenericService<ClientEntity> service;

    @ApiOperation(value = "Mostra lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", responseContainer = "List", response = ClientEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ClientEntity> clientEntityList = service.findAll();

        if(clientEntityList.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clientEntityList);

    }

    @ApiOperation(value = "Retorna os dados detalhados de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }

    @ApiOperation(value = "Salva as informações de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(path = Constants.ROUTE_SAVE)
    public ClientEntity create(@RequestBody ClientEntity client) {
        return service.save(client);
    }

    @ApiOperation(value = "Atualiza as informações de um novo cliente por id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PutMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<ClientEntity> updateById(@PathVariable("id") Long id, @RequestBody ClientEntity newClient) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        ClientEntity clientSave = client.get();
        clientSave.setEmail(newClient.getEmail());
        clientSave.setName(newClient.getName());

        return ResponseEntity.ok(service.save(clientSave));
    }

    @ApiOperation(value = "Deleta as informações de um cliente por id")
    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred")
    @DeleteMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {

        if(!service.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
