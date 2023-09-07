package it.movioletto.service.impl;

import it.movioletto.dto.StanzaDto;
import it.movioletto.mapper.StanzaMapper;
import it.movioletto.repository.StanzaRepository;
import it.movioletto.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

  private final StanzaRepository stanzaRepository;
  private final StanzaMapper stanzaMapper;

  public CommonServiceImpl(StanzaRepository stanzaRepository, StanzaMapper stanzaMapper) {
    this.stanzaRepository = stanzaRepository;
    this.stanzaMapper = stanzaMapper;
  }

  @Override
  public StanzaDto getStanza(String codiceStanza) {
    return stanzaRepository.findByCodice(codiceStanza)
        .map(stanzaMapper::toDto).orElse(null);
  }

}
