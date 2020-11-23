package br.com.luizalabsserverrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    List<T> findAllById(Iterable<Long> ids);

    boolean existsById(Long id);

    T save(T type);

    void deleteById(Long id);
}
