package it.movioletto.repository;

import it.movioletto.model.AnimaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimaleRepository extends JpaRepository<AnimaleEntity, Integer> {

}