package it.movioletto.repository;

import it.movioletto.model.AggettivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggettivoRepository extends JpaRepository<AggettivoEntity, Integer> {

}
