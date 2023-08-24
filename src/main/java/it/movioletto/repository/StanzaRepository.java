package it.movioletto.repository;

import it.movioletto.model.StanzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StanzaRepository extends JpaRepository<StanzaEntity, String> {

}
