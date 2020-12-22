package it.movioletto.repository;

import it.movioletto.model.AggettivoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AggettivoRepository extends CrudRepository<AggettivoEntity, Integer> {

}
