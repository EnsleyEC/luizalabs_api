package br.com.luizalabsserverrest.repository;

import br.com.luizalabsserverrest.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
}
