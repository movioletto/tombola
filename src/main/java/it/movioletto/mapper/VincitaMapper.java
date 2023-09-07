package it.movioletto.mapper;

import it.movioletto.dto.VincitaDto;
import it.movioletto.model.VincitaEntity;
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
public interface VincitaMapper {

  @Mapping(target = "idStanza", source = "stanza.idStanza")
  @Mapping(target = "tabella.idTabella", source = "tabella.idTabella")
  @Mapping(target = "tabella.sequenza", ignore = true)
  @Mapping(target = "premio", source = "premio")
  @Mapping(target = "nomePremio", ignore = true)
  VincitaDto toDto(VincitaEntity entity);

  @InheritInverseConfiguration
  @Mapping(target = "tabella.sequenza", ignore = true)
  VincitaEntity toEntity(VincitaDto dto);
}
