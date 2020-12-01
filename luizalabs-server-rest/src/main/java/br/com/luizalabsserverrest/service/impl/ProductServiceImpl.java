package br.com.luizalabsserverrest.service.impl;

import br.com.luizalabsserverrest.model.entity.ProductEntity;
import br.com.luizalabsserverrest.repository.ProductRepository;
import br.com.luizalabsserverrest.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements GenericService<ProductEntity> {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProductEntity> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public ProductEntity save(ProductEntity type) {
        return repository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ProductEntity> findAndFetchClientEntityListEagerly(Long id)
    {
        return repository.findAndFetchClientEntityListEagerly(id);
    }
}
