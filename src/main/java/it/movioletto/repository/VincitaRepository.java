package it.movioletto.repository;

import it.movioletto.model.VincitaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VincitaRepository extends JpaRepository<VincitaEntity, Integer> {

  @Query(value =
      " SELECT MAX(vincita.premio) " +
          " FROM VincitaEntity vincita " +
          " WHERE vincita.stanza.idStanza = :idStanza ")
  Integer findMaxPremio(@Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT vincita " +
          " FROM VincitaEntity vincita " +
          " WHERE vincita.stanza.idStanza = :idStanza ")
  List<VincitaEntity> findAllByIdStanza(@Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT vincita " +
          " FROM VincitaEntity vincita " +
          " WHERE vincita.stanza.idStanza = :idStanza " +
          "   AND vincita.premio = :idPremio ")
  Optional<VincitaEntity> findByIdStanzaAndPremio(@Param("idStanza") Integer idStanza,
      @Param("idPremio") Integer idPremio);
}