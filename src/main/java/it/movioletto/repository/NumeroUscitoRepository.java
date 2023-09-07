package it.movioletto.repository;

import it.movioletto.model.NumeroUscitoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NumeroUscitoRepository extends JpaRepository<NumeroUscitoEntity, Integer> {

  @Query(value = " SELECT numeroUscito "
      + " FROM NumeroUscitoEntity numeroUscito "
      + " WHERE numeroUscito.stanza.idStanza = :idStanza")
  List<NumeroUscitoEntity> findAllByIdStanza(@Param("idStanza") Integer idStanza);

  @Query(value = " SELECT numeroUscito "
      + " FROM NumeroUscitoEntity numeroUscito "
      + " WHERE numeroUscito.stanza.idStanza = :idStanza"
      + "   AND numeroUscito.numero = :numero ")
  Optional<NumeroUscitoEntity> countByIdStanzaAndNumero(@Param("idStanza") Integer idStanza,
      @Param("numero") Integer numero);
}
