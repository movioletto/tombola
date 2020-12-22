package it.movioletto.repository;

import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.NumeroUscitoEntityKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NumeroUscitoRepository extends CrudRepository<NumeroUscitoEntity, NumeroUscitoEntityKey> {

	@Query(value =
			" SELECT numeroUscito " +
					" FROM NumeroUscitoEntity numeroUscito " +
					" WHERE numeroUscito.id.idStanza = :idStanza " +
					" ORDER BY numeroUscito.data DESC ")
	Optional<List<NumeroUscitoEntity>> findByIdStanza(@Param("idStanza") String idStanza);
}
