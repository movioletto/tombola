package it.movioletto.repository;

import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.NumeroUscitoEntityKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumeroUscitoRepository extends
    CrudRepository<NumeroUscitoEntity, NumeroUscitoEntityKey> {

}
