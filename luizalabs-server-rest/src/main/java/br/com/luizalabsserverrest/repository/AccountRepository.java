package br.com.luizalabsserverrest.repository;

import br.com.luizalabsserverrest.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByUsername(String username);

}
