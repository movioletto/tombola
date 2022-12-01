package it.movioletto.service.impl;

import it.movioletto.dto.TabellaDto;
import it.movioletto.model.AggettivoEntity;
import it.movioletto.model.AnimaleEntity;
import it.movioletto.model.StanzaEntity;
import it.movioletto.model.TabellaEntity;
import it.movioletto.model.TabellaEntityKey;
import it.movioletto.repository.AggettivoRepository;
import it.movioletto.repository.AnimaleRepository;
import it.movioletto.repository.TabellaRepository;
import it.movioletto.service.CartellaService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CartellaServiceImpl implements CartellaService {

  private final Random randomNumeri = SecureRandom.getInstanceStrong();
  private final Random randomAnimale = SecureRandom.getInstanceStrong();
  private final Random randomAggettivo = SecureRandom.getInstanceStrong();
  private final AnimaleRepository animaleRepository;
  private final AggettivoRepository aggettivoRepository;
  private final TabellaRepository tabellaRepository;

  public CartellaServiceImpl(AnimaleRepository animaleRepository,
      AggettivoRepository aggettivoRepository, TabellaRepository tabellaRepository)
      throws NoSuchAlgorithmException {
    this.animaleRepository = animaleRepository;
    this.aggettivoRepository = aggettivoRepository;
    this.tabellaRepository = tabellaRepository;
  }

  @Override
  public TabellaDto creaTabella(String idStanza) {
    String idTabella = this.creaIdTabella(idStanza);

    String sequenza = this.creaSequenza(idStanza);

    TabellaEntityKey entityKey = TabellaEntityKey.builder()
        .idTabella(idTabella)
        .stanza(new StanzaEntity(idStanza))
        .build();

    TabellaEntity entity = TabellaEntity.builder()
        .id(entityKey)
        .sequenza(sequenza)
        .build();

    tabellaRepository.save(entity);

    TabellaDto out = new TabellaDto();
    out.setIdTabella(idTabella);
    out.setIdStanza(idStanza);
    out.setSequenza(sequenza);

    return out;
  }

  private String creaSequenza(String idStanza) {
    int numeroNumeri = 90;

    StringBuilder sequenza;
    do {
      Map<Integer, List<Integer>> numeriCartella = new HashMap<>();
      for (int i = 0; i < 9; i++) {
        numeriCartella.put(i, new ArrayList<>());
      }

      int numeroNumeriTrovati = 0;

      while (numeroNumeriTrovati < 15) {
        int numeroTrovato = randomNumeri.nextInt(numeroNumeri) + 1;

        int numeroColonna = (numeroTrovato / 10);

        if (StringUtils.endsWith(String.valueOf(numeroTrovato), "0")) {
          numeroColonna--;
        }

        if (numeriCartella.get(numeroColonna).size() < 3 && !numeriCartella.get(numeroColonna)
            .contains(numeroTrovato)) {
          numeriCartella.get(numeroColonna).add(numeroTrovato);
          numeroNumeriTrovati++;
        }
      }

      numeriCartella.forEach((chiave, valore) -> Collections.sort(valore));

      Map<Integer, Integer> totaleRigaMap = new HashMap<>();
      Map<Integer, List<Integer>> rigaMap = new HashMap<>();
      for (int i = 1; i < 4; i++) {
        totaleRigaMap.put(i, 0);
        rigaMap.put(i, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0)));
      }

      numeriCartella.forEach((chiave, valore) -> {
        if (valore.size() == 3) {
          for (int i = 1; i < 4; i++) {
            rigaMap.get(i).set(chiave, valore.get(i - 1));
            totaleRigaMap.replace(i, totaleRigaMap.get(i) + 1);
          }
        }
      });

      numeriCartella.forEach((chiave, valore) -> {
        if (valore.size() == 1) {
          Integer rigaPosizione = 1;
          for (int i = 1; i < 4; i++) {
            if (totaleRigaMap.get(i) < totaleRigaMap.get(rigaPosizione)) {
              rigaPosizione = i;
            }
          }

          rigaMap.get(rigaPosizione).set(chiave, valore.get(0));
          totaleRigaMap.replace(rigaPosizione, totaleRigaMap.get(rigaPosizione) + 1);
        }
      });

      numeriCartella.forEach((chiave, valore) -> {
        if (valore.size() == 2) {
          List<Integer> ordineValore = new ArrayList<>(totaleRigaMap.values());
          Collections.sort(ordineValore);

          int riga1 = totaleRigaMap.entrySet().stream()
              .filter(entry -> ordineValore.get(0).equals(entry.getValue()))
              .map(Map.Entry::getKey).findFirst().get();
          int finalRiga = riga1;
          int riga2 = totaleRigaMap.entrySet().stream()
              .filter(entry -> ordineValore.get(1).equals(entry.getValue())
                  && finalRiga != entry.getKey())
              .map(Map.Entry::getKey).findFirst().get();

          if (riga1 > riga2) {
            int temp = riga2;
            riga2 = riga1;
            riga1 = temp;
          }

          if (totaleRigaMap.get(riga1) < 5 && totaleRigaMap.get(riga2) < 5) {
            rigaMap.get(riga1).set(chiave, valore.get(0));
            totaleRigaMap.replace(riga1, totaleRigaMap.get(riga1) + 1);
            rigaMap.get(riga2).set(chiave, valore.get(1));
            totaleRigaMap.replace(riga2, totaleRigaMap.get(riga2) + 1);
          }

        }
      });

      sequenza = new StringBuilder();
      for (Integer chiave : rigaMap.keySet()) {
        List<Integer> valore = rigaMap.get(chiave);
        for (int i = 0; i < valore.size(); i++) {
          if (i != 0) {
            sequenza.append(",");
          }
          sequenza.append(valore.get(i));
        }
        if (chiave != 3) {
          sequenza.append("|");
        }
      }

    } while (this.existSequenza(sequenza.toString(), idStanza));
    return sequenza.toString();
  }

  private String creaIdTabella(String idStanza) {
    int numeroAnimali = (int) animaleRepository.count();
    int numeroAggettivi = (int) aggettivoRepository.count();

    String idTabella;
    do {
      Optional<AnimaleEntity> animale = animaleRepository.findById(
          randomAnimale.nextInt(numeroAnimali) + 1);
      String nomeAnimale = null;
      if (animale.isPresent()) {
        nomeAnimale = animale.get().getNome();
      }
      Optional<AggettivoEntity> aggettivo = aggettivoRepository.findById(
          randomAggettivo.nextInt(numeroAggettivi) + 1);
      String nomeAggettivo = null;
      if (aggettivo.isPresent()) {
        nomeAggettivo = aggettivo.get().getMaschile();
        if (animale.isPresent() && "f".equals(animale.get().getGenere())) {
          nomeAggettivo = aggettivo.get().getFemminile();
        }
      }

      idTabella = StringUtils.capitalize(nomeAnimale) + StringUtils.capitalize(nomeAggettivo);
    } while (this.existTabella(idTabella, idStanza));

    return idTabella;
  }

  @Override
  public boolean existTabella(String idTabella, String idStanza) {
    TabellaEntityKey entityKey = TabellaEntityKey.builder()
        .idTabella(idTabella)
        .stanza(new StanzaEntity(idStanza))
        .build();

    Optional<TabellaEntity> entityOptional = tabellaRepository.findById(entityKey);

    return entityOptional.isPresent();
  }

  @Override
  public boolean existSequenza(String sequenza, String idStanza) {
    Optional<TabellaEntity> entityOptional = tabellaRepository.findSequenzaStanza(sequenza,
        idStanza);

    return entityOptional.isPresent();
  }

  @Override
  public TabellaDto getTabella(String idTabella, String idStanza) {
    TabellaDto out = null;

    TabellaEntityKey entityKey = TabellaEntityKey.builder()
        .idTabella(idTabella)
        .stanza(new StanzaEntity(idStanza))
        .build();

    Optional<TabellaEntity> entity = tabellaRepository.findById(entityKey);

    if (entity.isPresent()) {
      out = new TabellaDto();
      out.setIdTabella(idTabella);
      out.setIdStanza(idStanza);
      out.setSequenza(entity.get().getSequenza());
    }

    return out;
  }

}
