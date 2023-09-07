package it.movioletto.mapper;

import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.model.NumeroUscitoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
    , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    , nullValueCheckStrategy = NullValueCheckStrategy.
    ALWAYS)
public interface NumeroUscitoMapper {

  @InheritInverseConfiguration
  NumeroUscitoDto toDto(NumeroUscitoEntity entity);

  @Mapping(source = "idStanza", target = "stanza.idStanza")
  @Mapping(source = "numero", target = "numero")
  NumeroUscitoEntity toEntity(NumeroUscitoDto dto);
}
