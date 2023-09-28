package it.movioletto.service.impl;

import it.movioletto.dto.AggettivoDto;
import it.movioletto.dto.AnimaleDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.mapper.AggettivoMapper;
import it.movioletto.mapper.AnimaleMapper;
import it.movioletto.mapper.StanzaMapper;
import it.movioletto.model.AnimaleEntity;
import it.movioletto.repository.AggettivoRepository;
import it.movioletto.repository.AnimaleRepository;
import it.movioletto.repository.StanzaRepository;
import it.movioletto.service.CommonService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

  private final StanzaRepository stanzaRepository;
  private final AnimaleRepository animaleRepository;
  private final AggettivoRepository aggettivoRepository;
  private final StanzaMapper stanzaMapper;
  private final AnimaleMapper animaleMapper;
  private final AggettivoMapper aggettivoMapper;

  public CommonServiceImpl(StanzaRepository stanzaRepository, AnimaleRepository animaleRepository,
      AggettivoRepository aggettivoRepository,
      StanzaMapper stanzaMapper, AnimaleMapper animaleMapper, AggettivoMapper aggettivoMapper) {
    this.stanzaRepository = stanzaRepository;
    this.animaleRepository = animaleRepository;
    this.aggettivoRepository = aggettivoRepository;
    this.stanzaMapper = stanzaMapper;
    this.animaleMapper = animaleMapper;
    this.aggettivoMapper = aggettivoMapper;
  }

  @Override
  public StanzaDto getStanza(String codiceStanza) {
    return stanzaRepository.findByCodice(codiceStanza)
        .map(stanzaMapper::toDto).orElse(null);
  }

  @Override
  public List<AnimaleDto> getAllAnimale() {
    return animaleRepository.findAll().stream().map(animaleMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<AggettivoDto> getAllAggettivo() {
    return aggettivoRepository.findAll().stream().map(aggettivoMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<AnimaleDto> getDieciAnimaleRandom() {
    List<AnimaleEntity> result = animaleRepository.findAll();

    Collections.shuffle(result);

    return result.stream().limit(10).map(animaleMapper::toDto).collect(Collectors.toList());
  }

}
