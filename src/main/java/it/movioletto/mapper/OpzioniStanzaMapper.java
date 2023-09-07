package it.movioletto.mapper;

import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.model.OpzioniStanzaEntity;
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
public interface OpzioniStanzaMapper {

  @Mapping(target = "idStanza", source = "stanza.idStanza")
  OpzioniStanzaDto toDto(OpzioniStanzaEntity entity);

  @InheritInverseConfiguration
  @Mapping(target = "stanza", ignore = true)
  OpzioniStanzaEntity toEntity(OpzioniStanzaDto dto);

}