package it.movioletto.repository;

import it.movioletto.model.VincitaEntity;
import it.movioletto.model.VincitaEntityKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VincitaRepository extends CrudRepository<VincitaEntity, VincitaEntityKey> {

  @Query(value =
      " SELECT MAX(vincita.id.premio) " +
          " FROM VincitaEntity vincita " +
          " WHERE vincita.id.stanza.idStanza = :idStanza ")
  Integer findMaxPremio(@Param("idStanza") String idStanza);

  Optional<List<VincitaEntity>> findAllByIdStanzaIdStanza(String idStanza);
}