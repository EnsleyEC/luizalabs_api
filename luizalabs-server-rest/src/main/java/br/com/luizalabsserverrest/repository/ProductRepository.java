package br.com.luizalabsserverrest.repository;

import br.com.luizalabsserverrest.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
