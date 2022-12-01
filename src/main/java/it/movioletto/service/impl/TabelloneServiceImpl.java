package it.movioletto.service.impl;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.VincitaDto;
import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.NumeroUscitoEntityKey;
import it.movioletto.model.StanzaEntity;
import it.movioletto.model.VincitaEntity;
import it.movioletto.model.VincitaEntityKey;
import it.movioletto.repository.NumeroUscitoRepository;
import it.movioletto.repository.StanzaRepository;
import it.movioletto.repository.VincitaRepository;
import it.movioletto.service.TabelloneService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TabelloneServiceImpl implements TabelloneService {

  private final Random random = SecureRandom.getInstanceStrong();
  private final StanzaRepository stanzaRepository;
  private final NumeroUscitoRepository numeroUscitoRepository;
  private final VincitaRepository vincitaRepository;
  private final ModelMapper modelMapper;

  public TabelloneServiceImpl(StanzaRepository stanzaRepository,
      NumeroUscitoRepository numeroUscitoRepository, VincitaRepository vincitaRepository,
      ModelMapper modelMapper) throws NoSuchAlgorithmException {
    this.stanzaRepository = stanzaRepository;
    this.numeroUscitoRepository = numeroUscitoRepository;
    this.vincitaRepository = vincitaRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public StanzaDto creaStanza(String nome) {
    int leftLimit = 48;
    int rightLimit = 122;
    int targetStringLength = 10;

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    StanzaEntity entity = StanzaEntity.builder()
        .idStanza(generatedString)
        .nome(nome)
        .build();

    return modelMapper.map(stanzaRepository.save(entity), StanzaDto.class);
  }

  @Override
  public StanzaDto creaStanza(String id, String nome) {
    StanzaEntity entity = StanzaEntity.builder()
        .idStanza(id)
        .nome(nome)
        .build();

    return modelMapper.map(stanzaRepository.save(entity), StanzaDto.class);
  }

  @Override
  public StanzaDto getStanza(String idStanza) {
    Optional<StanzaEntity> entityOptional = stanzaRepository.findById(idStanza);

    return entityOptional.map(stanzaEntity -> modelMapper.map(stanzaEntity, StanzaDto.class))
        .orElse(null);
  }

  @Override
  public List<NumeroUscitoDto> getNumeriUsciti(String idStanza) {
    List<NumeroUscitoDto> out = new ArrayList<>();

    Optional<StanzaEntity> stanzaEntityOptional = stanzaRepository.findById(idStanza);

    stanzaEntityOptional.ifPresent(stanzaEntity -> {
      if (!CollectionUtils.isEmpty(stanzaEntity.getNumeroUscitoList())) {
        stanzaEntity.getNumeroUscitoList().forEach(numeroUscitoEntity -> out.add(
            new NumeroUscitoDto(stanzaEntity.getIdStanza(), numeroUscitoEntity.getId().getNumero(),
                numeroUscitoEntity.getData())));
      }
    });

    return out;
  }

  @Override
  public void saveNumeroEstratto(String idStanza, int numeroEstratto) {
    NumeroUscitoEntityKey entityKey = NumeroUscitoEntityKey.builder()
        .stanza(new StanzaEntity(idStanza))
        .numero(numeroEstratto)
        .build();

    NumeroUscitoEntity entity = NumeroUscitoEntity.builder()
        .id(entityKey)
        .data(new Date())
        .build();

    numeroUscitoRepository.save(entity);
  }

  @Override
  public boolean existStanza(String idStanza) {
    Optional<StanzaEntity> entityOptional = stanzaRepository.findById(idStanza);

    return entityOptional.isPresent();
  }

  @Override
  public List<String> getGiocatoriPresenti(String idStanza) {
    List<String> out = new ArrayList<>();

    Optional<StanzaEntity> stanzaEntityOptional = stanzaRepository.findById(idStanza);

    stanzaEntityOptional.ifPresent(stanzaEntity -> {
      if (!CollectionUtils.isEmpty(stanzaEntity.getTabellaList())) {
        stanzaEntity.getTabellaList()
            .forEach(tabellaEntity -> out.add(tabellaEntity.getId().getIdTabella()));
      }
    });

    return out;
  }

  @Override
  public List<StanzaDto> getAllStanza() {
    List<StanzaDto> out = new ArrayList<>();

    Iterable<StanzaEntity> stanzaEntityIterable = stanzaRepository.findAll();

    stanzaEntityIterable.forEach(stanzaEntity -> {
      StanzaDto temp = modelMapper.map(stanzaEntity, StanzaDto.class);
      List<String> giocatoriPresenti = new ArrayList<>();
      List<NumeroUscitoDto> numeroUscitoList = new ArrayList<>();
      List<VincitaDto> vincitaList = new ArrayList<>();

      if (!CollectionUtils.isEmpty(stanzaEntity.getTabellaList())) {
        stanzaEntity.getTabellaList()
            .forEach(tabellaEntity -> giocatoriPresenti.add(tabellaEntity.getId().getIdTabella()));
      }

      if (!CollectionUtils.isEmpty(stanzaEntity.getNumeroUscitoList())) {
        stanzaEntity.getNumeroUscitoList().forEach(numeroUscitoEntity -> numeroUscitoList.add(
            new NumeroUscitoDto(stanzaEntity.getIdStanza(), numeroUscitoEntity.getId().getNumero(),
                numeroUscitoEntity.getData())));
      }

      if (!CollectionUtils.isEmpty(stanzaEntity.getVincitaList())) {
        stanzaEntity.getVincitaList().forEach(vincitaEntity -> vincitaList.add(
            new VincitaDto(stanzaEntity.getIdStanza(), vincitaEntity.getId().getIdTabella(),
                Objects.requireNonNull(
                    PremioEnum.getEnumByCodice(vincitaEntity.getId().getPremio())))));
      }

      temp.setGiocatorePresenteList(giocatoriPresenti);
      temp.setNumeroUscitoList(numeroUscitoList);
      temp.setVincitaList(vincitaList);

      out.add(temp);
    });

    return out;
  }

  @Override
  public List<VincitaDto> getVincite(String idStanza) {
    List<VincitaDto> out = new ArrayList<>();

    Optional<List<VincitaEntity>> vincitaEntityListOptional = vincitaRepository.findAllByIdStanzaIdStanza(
        idStanza);

    vincitaEntityListOptional.ifPresent(
        vincitaEntityList -> vincitaEntityList.forEach(vincitaEntity -> {
          VincitaDto temp = modelMapper.map(vincitaEntity, VincitaDto.class);
          temp.setPremio(vincitaEntity.getId().getPremio());
          temp.setNomePremio(PremioEnum.getValoreByCodice(temp.getPremio()));

          out.add(temp);
        }));

    return out;
  }

  @Override
  public PremioEnum getPremioCorrente(String idStanza) {
    Integer premio = vincitaRepository.findMaxPremio(idStanza);

    if (premio == null) {
      return PremioEnum.AMBO;
    } else if (premio == 6) {
      return null;
    }

    return PremioEnum.getEnumByCodice(premio + 1);
  }

  @Override
  public boolean existPremio(String idStanza, String idTabella, Integer idPremio) {
    VincitaEntityKey entityKey = VincitaEntityKey.builder()
        .stanza(new StanzaEntity(idStanza))
        .idTabella(idTabella)
        .premio(idPremio)
        .build();

    Optional<VincitaEntity> entityOptional = vincitaRepository.findById(entityKey);

    return entityOptional.isPresent();
  }

  @Override
  public void savePremio(String idStanza, String idTabella, Integer idPremio) {
    VincitaEntityKey entityKey = VincitaEntityKey.builder()
        .stanza(new StanzaEntity(idStanza))
        .idTabella(idTabella)
        .premio(idPremio)
        .build();

    VincitaEntity entity = VincitaEntity.builder()
        .id(entityKey)
        .build();

    vincitaRepository.save(entity);
  }
}
