package br.com.luizalabsserverrest.repository;

import br.com.luizalabsserverrest.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {


    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.clientEntityList c WHERE c.id = (:id)")
    List<ProductEntity> findAndFetchClientEntityListEagerly(@Param("id") Long id);

}
