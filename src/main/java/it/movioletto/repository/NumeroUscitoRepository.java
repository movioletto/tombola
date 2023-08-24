package it.movioletto.repository;

import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.NumeroUscitoEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumeroUscitoRepository extends
    JpaRepository<NumeroUscitoEntity, NumeroUscitoEntityKey> {

}
