package it.movioletto.repository;

import it.movioletto.model.StanzaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StanzaRepository extends JpaRepository<StanzaEntity, String> {

  Optional<StanzaEntity> findByCodice(@Param("codice") String codice);

}
