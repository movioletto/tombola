package it.movioletto.repository;

import it.movioletto.model.VincitaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VincitaRepository extends CrudRepository<VincitaEntity, String> {
}