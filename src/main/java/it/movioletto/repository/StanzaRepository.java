package it.movioletto.repository;

import it.movioletto.model.StanzaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StanzaRepository extends CrudRepository<StanzaEntity, String> {

}
