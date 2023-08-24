package it.movioletto.repository;

import it.movioletto.model.TabellaEntity;
import it.movioletto.model.TabellaEntityKey;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TabellaRepository extends JpaRepository<TabellaEntity, TabellaEntityKey> {

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.id.stanza.idStanza = :idStanza " +
          "   and tabellaEntity.sequenza = :sequenza ")
  Optional<TabellaEntity> findSequenzaStanza(@Param("sequenza") String sequenza,
      @Param("idStanza") String idStanza);

}