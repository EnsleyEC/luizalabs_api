package br.com.luizalabsserverrest.repository;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

    boolean existsByEmail(String email);

    Optional<ClientEntity> findByEmail(String email);

}
