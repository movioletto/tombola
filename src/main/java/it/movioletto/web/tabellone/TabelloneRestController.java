package it.movioletto.web.tabellone;

import it.movioletto.constant.AzioneEnum;
import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.OpzioniStanzaDto;
import it.movioletto.dto.PremioDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.service.CartellaService;
import it.movioletto.service.CommonService;
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
  private final CommonService commonService;
  private final Random random = SecureRandom.getInstanceStrong();

  public TabelloneRestController(SimpMessagingTemplate simpMessagingTemplate,
      TabelloneService tabelloneService, CartellaService cartellaService,
      CommonService commonService)
      throws NoSuchAlgorithmException {
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.tabelloneService = tabelloneService;
    this.cartellaService = cartellaService;
    this.commonService = commonService;
  }

  @GetMapping("/stanza/{codiceStanza}/numero")
  public MessaggioDto getNumeroTabellone(@PathVariable("codiceStanza") String codiceStanza) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);
    if (stanza == null) {
      return null;
    }

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(stanza.getIdStanza());

    int numeroEstratto;
    do {
      numeroEstratto = random.nextInt(90) + 1;
    } while (numeriUsciti.contains(new NumeroUscitoDto(numeroEstratto)));

    tabelloneService.saveNumeroEstratto(stanza.getIdStanza(), numeroEstratto);

    MessaggioDto messaggioDto = MessaggioDto.builder()
        .azione(AzioneEnum.NUMERO_USCITO.getCodice())
        .numeroUscito(numeroEstratto)
        .build();

    simpMessagingTemplate.convertAndSend("/partita/stanza/" + codiceStanza, messaggioDto);

    return messaggioDto;
  }

  @GetMapping("/stanza/{codiceStanza}/tabella/{idTabella}")
  public TabellaDto getTabellaStanza(@PathVariable("codiceStanza") String codiceStanza,
      @PathVariable("idTabella") Integer idTabella) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);

    if (stanza == null || cartellaService.isNotExistTabella(idTabella, stanza.getIdStanza())) {
      return null;
    }

    TabellaDto tabella = cartellaService.getTabella(idTabella, stanza.getIdStanza());
    tabella.setNumeriUsciti(tabelloneService.getNumeriUsciti(stanza.getIdStanza()));

    return tabella;
  }

  @GetMapping("/stanza/{codiceStanza}/premioCorrente")
  public MessaggioDto getPremioCorrente(@PathVariable("codiceStanza") String codiceStanza) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);
    if (stanza == null) {
      return null;
    }

    PremioDto premioCorrente = tabelloneService.getPremioCorrente(stanza.getIdStanza(),
        stanza.getOpzioniStanza());

    if (premioCorrente == null) {
      return null;
    }

    return MessaggioDto.builder()
        .idPremio(premioCorrente.getCodice())
        .nomePremio(premioCorrente.getValore())
        .build();
  }

  @GetMapping("/stanza/{codiceStanza}/premio/{idTabella}/{idPremio}")
  public MessaggioDto savePremio(@PathVariable("codiceStanza") String codiceStanza,
      @PathVariable("idTabella") Integer idTabella, @PathVariable("idPremio") Integer idPremio) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);
    if (stanza == null || cartellaService.isNotExistTabella(idTabella, stanza.getIdStanza())
        || tabelloneService.existPremio(stanza.getIdStanza(), idTabella, idPremio)) {
      return null;
    }

    tabelloneService.savePremio(stanza.getIdStanza(), idTabella, idPremio);
    TabellaDto tabella = cartellaService.getTabella(idTabella, stanza.getIdStanza());

    return MessaggioDto.builder()
        .azione(4)
        .idTabella(idTabella)
        .nomePremio(stanza.getOpzioniStanza().getPremiCustom().get(idPremio))
        .idPremio(idPremio)
        .nomeTabella(tabella.getNome())
        .aggettivoTabella(tabella.getAggettivo())
        .build();
  }

  @GetMapping("/opzioni/stanza/{codiceStanza}")
  public OpzioniStanzaDto opzioniStanza(@PathVariable("codiceStanza") String codiceStanza) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);
    if (stanza == null) {
      return null;
    }

    return stanza.getOpzioniStanza();
  }

}
