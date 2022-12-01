package it.movioletto.web.tabellone;

import it.movioletto.constant.AzioneEnum;
import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tabellone")
public class TabelloneRestController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  private final TabelloneService tabelloneService;

  private final CartellaService cartellaService;

  private final Random random = SecureRandom.getInstanceStrong();

  public TabelloneRestController(SimpMessagingTemplate simpMessagingTemplate,
      TabelloneService tabelloneService, CartellaService cartellaService)
      throws NoSuchAlgorithmException {
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.tabelloneService = tabelloneService;
    this.cartellaService = cartellaService;
  }

  @GetMapping("/stanza/{idStanza}/numero")
  public MessaggioDto getNumeroTabellone(@PathVariable("idStanza") String idStanza) {

    if (!tabelloneService.existStanza(idStanza)) {
      return null;
    }

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

    int numeroEstratto;
    do {
      numeroEstratto = random.nextInt(90) + 1;
    } while (numeriUsciti.contains(new NumeroUscitoDto(numeroEstratto)));

    tabelloneService.saveNumeroEstratto(idStanza, numeroEstratto);

    MessaggioDto messaggioDto = MessaggioDto.builder()
        .azione(AzioneEnum.NUMERO_USCITO.getCodice())
        .numeroUscito(numeroEstratto)
        .build();

    simpMessagingTemplate.convertAndSend("/partita/stanza/" + idStanza, messaggioDto);

    return messaggioDto;
  }

  @GetMapping("/stanza/{idStanza}/tabella/{idTabella}")
  public TabellaDto getTabellaStanza(@PathVariable("idStanza") String idStanza,
      @PathVariable("idTabella") String idTabella) {

    if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella,
        idStanza)) {
      return null;
    }

    TabellaDto tabella = cartellaService.getTabella(idTabella, idStanza);
    tabella.setNumeriUsciti(tabelloneService.getNumeriUsciti(idStanza));

    return tabella;
  }

  @GetMapping("/stanza/{idStanza}/premioCorrente")
  public MessaggioDto getPremioCorrente(@PathVariable("idStanza") String idStanza) {

    if (!tabelloneService.existStanza(idStanza)) {
      return null;
    }

    PremioEnum premioCorrente = tabelloneService.getPremioCorrente(idStanza);

    if (premioCorrente == null) {
      return null;
    }

    return MessaggioDto.builder()
        .idPremio(premioCorrente.getCodice())
        .nomePremio(premioCorrente.getValore())
        .build();
  }

  @GetMapping("/stanza/{idStanza}/premio/{idTabella}/{idPremio}")
  public MessaggioDto savePremio(@PathVariable("idStanza") String idStanza,
      @PathVariable("idTabella") String idTabella, @PathVariable("idPremio") Integer idPremio) {

    if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella,
        idStanza) || tabelloneService.existPremio(idStanza, idTabella, idPremio)) {
      return null;
    }

    tabelloneService.savePremio(idStanza, idTabella, idPremio);

    return MessaggioDto.builder()
        .azione(4)
        .idTabella(idTabella)
        .nomePremio(PremioEnum.getValoreByCodice(idPremio))
        .idPremio(idPremio)
        .build();
  }

}
