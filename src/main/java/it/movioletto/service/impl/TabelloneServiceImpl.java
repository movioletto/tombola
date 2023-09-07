package it.movioletto.service.impl;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.dto.PremioDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.dto.VincitaDto;
import it.movioletto.mapper.OpzioniStanzaMapper;
import it.movioletto.mapper.StanzaMapper;
import it.movioletto.mapper.TabellaMapper;
import it.movioletto.mapper.VincitaMapper;
import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.OpzioniStanzaEntity;
import it.movioletto.model.StanzaEntity;
import it.movioletto.model.TabellaEntity;
import it.movioletto.model.VincitaEntity;
import it.movioletto.repository.NumeroUscitoRepository;
import it.movioletto.repository.OpzioniStanzaRepository;
import it.movioletto.repository.StanzaRepository;
import it.movioletto.repository.TabellaRepository;
import it.movioletto.repository.VincitaRepository;
import it.movioletto.service.TabelloneService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TabelloneServiceImpl implements TabelloneService {

  private final Random random = SecureRandom.getInstanceStrong();
  private final StanzaRepository stanzaRepository;
  private final NumeroUscitoRepository numeroUscitoRepository;
  private final VincitaRepository vincitaRepository;
  private final TabellaRepository tabellaRepository;
  private final OpzioniStanzaRepository opzioniStanzaRepository;
  private final OpzioniStanzaMapper opzioniStanzaMapper;
  private final StanzaMapper stanzaMapper;
  private final TabellaMapper tabellaMapper;
  private final VincitaMapper vincitaMapper;

  public TabelloneServiceImpl(StanzaRepository stanzaRepository,
      NumeroUscitoRepository numeroUscitoRepository, VincitaRepository vincitaRepository,
      TabellaRepository tabellaRepository, OpzioniStanzaRepository opzioniStanzaRepository,
      OpzioniStanzaMapper opzioniStanzaMapper, StanzaMapper stanzaMapper,
      TabellaMapper tabellaMapper, VincitaMapper vincitaMapper)
      throws NoSuchAlgorithmException {
    this.stanzaRepository = stanzaRepository;
    this.numeroUscitoRepository = numeroUscitoRepository;
    this.vincitaRepository = vincitaRepository;
    this.tabellaRepository = tabellaRepository;
    this.opzioniStanzaRepository = opzioniStanzaRepository;
    this.opzioniStanzaMapper = opzioniStanzaMapper;
    this.stanzaMapper = stanzaMapper;
    this.tabellaMapper = tabellaMapper;
    this.vincitaMapper = vincitaMapper;
  }

  @Override
  public StanzaDto creaStanza(StanzaDto dto) {
    StanzaEntity entity = StanzaEntity.builder()
        .codice(this.getCodiceStanza(dto,
            BooleanUtils.isTrue(dto.getOpzioniStanza().getCodiceStanzaCustom())))
        .nome(StringUtils.abbreviate(dto.getNome(), 200))
        .build();

    entity = stanzaRepository.save(entity);

    this.puliziaOpzioniStanza(dto.getOpzioniStanza());

    OpzioniStanzaEntity opzioniStanzaEntity = opzioniStanzaMapper.toEntity(dto.getOpzioniStanza());
    opzioniStanzaEntity.setStanza(entity);

    entity.setOpzioniStanza(opzioniStanzaRepository.save(opzioniStanzaEntity));

    return stanzaMapper.toDto(entity);
  }

  private void puliziaOpzioniStanza(OpzioniStanzaDto opzioniStanza) {
    if (BooleanUtils.isFalse(opzioniStanza.getNomiTabellaCustom())) {
      opzioniStanza.setIconeTabella(false);
    }

    if (BooleanUtils.isFalse(opzioniStanza.getTombolino())) {
      opzioniStanza.getPremiCustom().replace(PremioEnum.TOMBOLINO.getCodice(), null);
    }

    opzioniStanza.getPremiCustom().entrySet().forEach(premio -> {
      if (StringUtils.isBlank(premio.getValue())) {
        premio.setValue(PremioEnum.getValoreByCodice(premio.getKey()));
      }
    });
  }

  private String getCodiceStanza(StanzaDto dto, Boolean codiceStanzaCustom) {
    String codice =
        codiceStanzaCustom ? StringUtils.truncate(dto.getCodice(), 20) : generaUnaStringaCausale(4);

    int i = 0;
    while (this.existStanzaByCodice(codice)) {
      if (codiceStanzaCustom) {
        codice = StringUtils.truncate(codice, 20 - StringUtils.length(String.valueOf(i))) + i++;
      } else {
        codice = generaUnaStringaCausale(4);
        i++;
      }

      if (i > 5) {
        codice = generaUnaStringaCausale(5);
      }
    }

    return codice;
  }

  private String generaUnaStringaCausale(int targetStringLength) {
    int leftLimit = 48;
    int rightLimit = 122;

    return StringUtils.upperCase(random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString());
  }

  @Override
  public List<NumeroUscitoDto> getNumeriUsciti(Integer idStanza) {
    return numeroUscitoRepository.findAllByIdStanza(idStanza).stream().map(
        numeroUscitoEntity -> new NumeroUscitoDto(numeroUscitoEntity.getIdNumeroUscito(), idStanza,
            numeroUscitoEntity.getNumero(),
            numeroUscitoEntity.getData())).collect(Collectors.toList());
  }

  @Override
  public void saveNumeroEstratto(Integer idStanza, int numeroEstratto) {
    if (numeroUscitoRepository.countByIdStanzaAndNumero(idStanza, numeroEstratto).isPresent()) {
      return;
    }

    NumeroUscitoEntity entity = NumeroUscitoEntity.builder()
        .stanza(new StanzaEntity(idStanza))
        .numero(numeroEstratto)
        .data(new Date())
        .build();

    numeroUscitoRepository.save(entity);
  }

  @Override
  public boolean existStanzaByCodice(String codice) {
    return stanzaRepository.findByCodice(codice).isPresent();
  }

  @Override
  public List<TabellaDto> getGiocatoriPresenti(Integer idStanza) {
    return tabellaRepository.findAllByIdStanza(idStanza).stream()
        .map(tabellaMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<StanzaDto> getAllStanza() {
    return stanzaRepository.findAll().stream().map(stanzaEntity -> {
      StanzaDto stanza = stanzaMapper.toDto(stanzaEntity);

      if (!CollectionUtils.isEmpty(stanzaEntity.getTabellaList())) {
        stanza.setGiocatorePresenteList(stanzaEntity.getTabellaList().stream()
            .map(tabellaMapper::toDto)
            .collect(Collectors.toList()));
      }

      if (!CollectionUtils.isEmpty(stanzaEntity.getNumeroUscitoList())) {
        stanza.setNumeroUscitoList(stanzaEntity.getNumeroUscitoList().stream().map(
                numeroUscitoEntity -> new NumeroUscitoDto(numeroUscitoEntity.getIdNumeroUscito(),
                    stanzaEntity.getIdStanza(),
                    numeroUscitoEntity.getNumero(), numeroUscitoEntity.getData()))
            .collect(Collectors.toList()));
      }

      if (!CollectionUtils.isEmpty(stanzaEntity.getVincitaList())) {
        stanza.setVincitaList(stanzaEntity.getVincitaList().stream().map(
                vincitaEntity -> new VincitaDto(vincitaEntity.getIdVincita(),
                    stanzaEntity.getIdStanza(),
                    tabellaMapper.toDto(vincitaEntity.getTabella()), Objects.requireNonNull(
                    PremioEnum.getEnumByCodice(vincitaEntity.getPremio()))))
            .collect(Collectors.toList()));
      }

      return stanza;
    }).collect(Collectors.toList());
  }

  @Override
  public List<VincitaDto> getVincite(Integer idStanza, OpzioniStanzaDto opzioniStanza) {
    return vincitaRepository.findAllByIdStanza(idStanza).stream().map(vincitaEntity -> {
      VincitaDto vincitaDto = vincitaMapper.toDto(vincitaEntity);
      vincitaDto.setPremio(vincitaEntity.getPremio());
      vincitaDto.setNomePremio(opzioniStanza.getPremiCustom().get(vincitaDto.getPremio()));

      return vincitaDto;
    }).collect(Collectors.toList());
  }

  @Override
  public PremioDto getPremioCorrente(Integer idStanza, OpzioniStanzaDto opzioniStanza) {
    Integer premio = vincitaRepository.findMaxPremio(idStanza);

    if (premio == null) {
      return new PremioDto(PremioEnum.AMBO.getCodice(),
          opzioniStanza.getPremiCustom().get(PremioEnum.AMBO.getCodice()));
    } else if ((PremioEnum.TOMBOLA.getCodice().equals(premio) && !BooleanUtils.isTrue(
        opzioniStanza.getTombolino())) || (PremioEnum.TOMBOLINO.getCodice().equals(premio)
        && BooleanUtils.isTrue(opzioniStanza.getTombolino()))) {
      return null;
    }

    return new PremioDto(premio + 1, opzioniStanza.getPremiCustom().get(premio + 1));
  }

  @Override
  public boolean existPremio(Integer idStanza, Integer idTabella, Integer idPremio) {
    return vincitaRepository.findByIdStanzaAndPremio(idStanza, idPremio).isPresent();
  }

  @Override
  public void savePremio(Integer idStanza, Integer idTabella, Integer idPremio) {
    if (vincitaRepository.findByIdStanzaAndPremio(idStanza, idPremio).isPresent()) {
      return;
    }

    VincitaEntity entity = VincitaEntity.builder()
        .stanza(new StanzaEntity(idStanza))
        .tabella(TabellaEntity.builder().idTabella(idTabella).build())
        .premio(idPremio)
        .build();

    vincitaRepository.save(entity);
  }
}
