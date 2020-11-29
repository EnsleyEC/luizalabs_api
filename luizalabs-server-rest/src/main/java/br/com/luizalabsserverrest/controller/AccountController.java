package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.AccountRequest;
import br.com.luizalabsserverrest.controller.response.AccountResponse;
import br.com.luizalabsserverrest.controller.response.MessageResponse;
import br.com.luizalabsserverrest.model.entity.AccountEntity;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.service.impl.AccountServiceImpl;
import br.com.luizalabsserverrest.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api(value = "Account")
@RequestMapping(path = Constants.ROUTE_ACCOUNT)
public class AccountController {

    @Autowired
    private AccountServiceImpl service;

    @ApiOperation(value = "Salva as informações de uma nova conta")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = AccountResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(path = Constants.ROUTE_SAVE)
    public ResponseEntity<?> create(@Valid @RequestBody AccountRequest accountRequest) {

        AccountEntity accountEntity = AccountRequest.convertToEntity(accountRequest);

        if(service.existsByUsername(accountRequest.getUsername()))
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Usuário já existe."));
        }

        return ResponseEntity.ok(AccountResponse.convertToResponse(service.save(accountEntity)));

    }

    @ApiOperation(value = "Deleta as informações de uma conta")
    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred")
    @DeleteMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        if(!service.existsById(id)) {

            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
