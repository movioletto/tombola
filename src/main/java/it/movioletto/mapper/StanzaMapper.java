package it.movioletto.mapper;

import it.movioletto.dto.StanzaDto;
import it.movioletto.model.StanzaEntity;
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
public interface StanzaMapper {

  @Mapping(target = "vincitaList", ignore = true)
  StanzaDto toDto(StanzaEntity entity);

  @InheritInverseConfiguration
  @Mapping(target = "vincitaList", ignore = true)
  StanzaEntity toEntity(StanzaDto dto);
}
