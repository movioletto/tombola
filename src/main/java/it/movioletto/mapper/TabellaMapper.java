package it.movioletto.mapper;

import it.movioletto.dto.TabellaDto;
import it.movioletto.model.TabellaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
    , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TabellaMapper {

  @Mapping(target = "idTabella", source = "idTabella")
  @Mapping(target = "idStanza", source = "stanza.idStanza")
  @Mapping(target = "codiceStanza", source = "stanza.codice")
  @Mapping(target = "sequenza", ignore = true)
  TabellaDto toDto(TabellaEntity entity);

  @InheritInverseConfiguration
  @Mapping(target = "sequenza", ignore = true)
  TabellaEntity toEntity(TabellaDto dto);

}
