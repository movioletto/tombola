package it.movioletto.repository;

import it.movioletto.model.AnimaleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaleRepository extends CrudRepository<AnimaleEntity, Integer> {

}