package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.controller.request.ProductRequest;
import br.com.luizalabsserverrest.controller.response.ProductResponse;
import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.repository.ProductRepository;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.util.Constants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Product")
@RequestMapping(path = Constants.ROUTE_PRODUCT)
public class ProductController {

    @Autowired
    private GenericService<ProductEntity> service;

    @Autowired
    private GenericService<ClientEntity> clientService;

    @Autowired
    private ProductRepository repository;

    @ApiOperation(value = "Mostra lista de produtos por página de tamanho 5, iniciando em 1")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", responseContainer = "List", response = ProductResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping
    public ResponseEntity<?> findByPageSortedByTitle(
            @ApiParam(
            name =  "page",
            type = "Integer",
            value = "Página dos dados, iniciando em 1",
            example = "1",
            required = true)
            @RequestParam Integer page) {

        // Primeira página é 0, se mandar a página 1, será buscada a 0.
        page -= 1;

        Pageable sortedByName =
                PageRequest.of(page, 5, Sort.by("title"));

        List<ProductEntity> productEntityList = service.findAll(sortedByName).toList();

        if(productEntityList.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ProductResponse.convertToResponseList(productEntityList));
    }

    @ApiOperation(value = "Retorna os dados detalhados de um produto por id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ProductResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Optional<ProductEntity> product = service.findById(id);

        if(!product.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ProductResponse.convertToResponse(product.get()));
    }

    @ApiOperation(value = "Retorna a lista de produtos favoritos de um cliente, usando seu id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", responseContainer = "List",response = ProductResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @GetMapping(path = Constants.ROUTE_FIND_FAVORITE_PRODUCTS_BY_CLIENT)
    public ResponseEntity<?> findFavoriteProductListByClientId(@PathVariable("id") Long id) {

        Optional<ClientEntity> clientEntity = clientService.findById(id);

        if(!clientEntity.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findAndFetchClientEntityListEagerly(id));

    }

    @ApiOperation(value = "Salva as informações de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ok", response = ProductResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "An unexpected error occurred") })
    @PostMapping(path = Constants.ROUTE_SAVE)
    public ProductResponse create(@RequestBody ProductRequest productRequest) {

        ProductEntity productEntity = ProductRequest.convertToEntity(productRequest);

        return ProductResponse.convertToResponse(service.save(productEntity));

    }

    @ApiOperation(value = "Deleta as informações de um produto por id")
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
