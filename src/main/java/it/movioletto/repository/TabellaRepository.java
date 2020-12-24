package it.movioletto.repository;

import it.movioletto.model.TabellaEntity;
import it.movioletto.model.TabellaEntityKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabellaRepository extends CrudRepository<TabellaEntity, TabellaEntityKey> {

	@Query(value =
			" SELECT tabellaEntity " +
					" FROM TabellaEntity tabellaEntity " +
					" WHERE tabellaEntity.id.stanza.idStanza = :idStanza " +
					"   and tabellaEntity.sequenza = :sequenza ")
	Optional<TabellaEntity> findSequenzaStanza(@Param("sequenza") String sequenza, @Param("idStanza") String idStanza);

}