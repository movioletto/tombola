package it.movioletto.mapper;

import it.movioletto.dto.AggettivoDto;
import it.movioletto.model.AggettivoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
    , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    , nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
    , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AggettivoMapper {

  AggettivoDto toDto(AggettivoEntity entity);

  @InheritInverseConfiguration
  AggettivoEntity toEntity(AggettivoDto dto);
}
