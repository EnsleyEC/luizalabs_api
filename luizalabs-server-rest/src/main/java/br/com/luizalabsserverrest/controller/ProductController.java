package br.com.luizalabsserverrest.controller;

import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.service.GenericService;
import br.com.luizalabsserverrest.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = Constants.ROUTE_PRODUCT)
public class ProductController {

    @Autowired
    private GenericService<ProductEntity> service;

    @GetMapping
    public List<?> findByPageSortedByTitle(@RequestParam Integer page) {

        // Primeira página é 0, se mandar a página 1, será buscada a 0.
        page -= 1;

        Pageable sortedByName =
                PageRequest.of(page, 5, Sort.by("title"));

        return service.findAll(sortedByName).toList();
    }

    @GetMapping(path = Constants.ROUTE_ID)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        Optional<ProductEntity> product = service.findById(id);

        if(!product.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product.get());
    }

    @PostMapping(path = Constants.ROUTE_SAVE)
    public ProductEntity create(@RequestBody ProductEntity client) {
        return service.save(client);
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
