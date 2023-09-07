package it.movioletto.repository;

import it.movioletto.model.TabellaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TabellaRepository extends JpaRepository<TabellaEntity, Integer> {

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.stanza.idStanza = :idStanza " +
          "   and tabellaEntity.sequenza = :sequenza ")
  Optional<TabellaEntity> findSequenzaStanza(@Param("sequenza") String sequenza,
      @Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.stanza.idStanza = :idStanza")
  List<TabellaEntity> findAllByIdStanza(@Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.stanza.idStanza = :idStanza "
          + " AND tabellaEntity.nome = :nome ")
  Optional<TabellaEntity> findByNomeTabellaAndIdStanza(@Param("nome") String nome,
      @Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.stanza.idStanza = :idStanza "
          + " AND tabellaEntity.nome = :nome "
          + " AND tabellaEntity.aggettivo = :aggettivo ")
  Optional<TabellaEntity> findByNomeAggettivoTabellaAndIdStanza(@Param("nome") String nome,
      @Param("aggettivo") String aggettivo, @Param("idStanza") Integer idStanza);

  @Query(value =
      " SELECT tabellaEntity " +
          " FROM TabellaEntity tabellaEntity " +
          " WHERE tabellaEntity.stanza.idStanza = :idStanza "
          + " AND tabellaEntity.idTabella = :idTabella ")
  Optional<TabellaEntity> findByIdTabellaAndIdStanza(@Param("idTabella") Integer idTabella,
      @Param("idStanza") Integer idStanza);

}