package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.ClientRequest;
import br.com.luizalabsserverrest.controller.request.FavoriteProductsRequest;
import br.com.luizalabsserverrest.controller.response.ClientResponse;
import br.com.luizalabsserverrest.controller.response.MessageResponse;
import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.service.impl.ClientServiceImpl;
import br.com.luizalabsserverrest.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Client")
@RequestMapping(path = Constants.ROUTE_CLIENT)
public class ClientController {

    @Autowired
    private ClientServiceImpl service;

    @Autowired
    private GenericService<ProductEntity> productService;

    @ApiOperation(value = "Mostra lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", responseContainer = "List", response = ClientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping
    public ResponseEntity<?> findAll() {

        List<ClientEntity> clientEntityList = service.findAll();

        if(clientEntityList.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ClientResponse.convertToResponseList(clientEntityList));

    }

    @ApiOperation(value = "Retorna os dados detalhados de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ClientResponse.convertToResponse(client.get()));
    }

    @ApiOperation(value = "Salva as informações de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(path = Constants.ROUTE_SAVE)
    public ResponseEntity<?> create(@RequestBody ClientRequest client) {

        ClientEntity clientEntity = ClientRequest.convertToEntity(client);

        if(service.existsByEmail(client.getEmail()))
        {
            return ResponseEntity.badRequest().body(new MessageResponse("E-mail já existe."));
        }

        service.save(clientEntity);

        return ResponseEntity.ok(ClientResponse.convertToResponse(service.save(clientEntity)));

    }

    @ApiOperation(value = "Atualiza as informações de um novo cliente por id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PutMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ClientRequest newClient) {

        Optional<ClientEntity> client = service.findById(id);

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        Optional<ClientEntity> clientWithEmail = service.findByEmail(newClient.getEmail());

        if(clientWithEmail.isPresent() && clientWithEmail.get().getId() != id)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("E-mail já existe."));
        }

        ClientEntity clientSave = ClientRequest.convertToEntity(newClient);
        clientSave.setId(id);

        return ResponseEntity.ok(ClientResponse.convertToResponse(service.save(clientSave)));

    }

    @ApiOperation(value = "Adiciona os produtos favoritos ao cliente")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ClientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(path = Constants.ROUTE_ADD_FAVORITE_PRODUCTS_BY_CLIENT)
    public ResponseEntity<ClientResponse> addFavoriteProductsToClient(@RequestBody FavoriteProductsRequest favoriteProductsRequest) {

        Optional<ClientEntity> client = service.findById(favoriteProductsRequest.getClientId());

        if(!client.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        ClientEntity clientSave = client.get();

        List<ProductEntity> productEntityList = productService.findAllById(favoriteProductsRequest.getFavoriteProductsIds());

        clientSave.setFavoriteProductsList(productEntityList);

        service.save(clientSave);

        return ResponseEntity.ok().build();

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
