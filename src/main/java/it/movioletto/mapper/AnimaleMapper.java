package it.movioletto.mapper;

import it.movioletto.dto.AnimaleDto;
import it.movioletto.model.AnimaleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
    , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AnimaleMapper {

  AnimaleDto toDto(AnimaleEntity entity);

  @InheritInverseConfiguration
  AnimaleEntity toEntity(AnimaleDto dto);
}
