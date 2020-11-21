package br.com.luizalabsserverrest.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    boolean existsById(Long id);

    T save(T type);

    void deleteById(Long id);
}
