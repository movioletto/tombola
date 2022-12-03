package it.movioletto.web.cartella;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.cartella.data.CartellaData;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cartella")
@Log
public class CartellaController {

  private final CartellaService cartellaService;

  private final TabelloneService tabelloneService;

  public CartellaController(CartellaService cartellaService, TabelloneService tabelloneService) {
    this.cartellaService = cartellaService;
    this.tabelloneService = tabelloneService;
  }

  @GetMapping("/new")
  public String getNew() {
    return "cartella/new";
  }

  @PostMapping("/newAct")
  public String getNewActPost(String idStanza) {
    if (!tabelloneService.existStanza(idStanza)) {
      return "redirect:/";
    }
    TabellaDto tabellaDto = cartellaService.creaTabella(idStanza, null);

    return "redirect:/cartella/stanza/" + tabellaDto.getIdStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/new/{idStanza}")
  public String getNewAct(@PathVariable("idStanza") String idStanza) {
    if (!tabelloneService.existStanza(idStanza)) {
      return "redirect:/";
    }
    TabellaDto tabellaDto = cartellaService.creaTabella(idStanza, null);

    return "redirect:/cartella/stanza/" + tabellaDto.getIdStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/custom")
  public String getCustom() {
    return "cartella/custom";
  }

  @PostMapping("/customAct")
  public String getCustomActPost(String idStanza, String idTabella) {
    if (!tabelloneService.existStanza(idStanza)) {
      return "redirect:/";
    }
    TabellaDto tabellaDto = cartellaService.creaTabella(idStanza, idTabella);

    return "redirect:/cartella/stanza/" + tabellaDto.getIdStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/custom/{idStanza}")
  public String getCustomAct(Model model, @PathVariable("idStanza") String idStanza) {
    if (!tabelloneService.existStanza(idStanza)) {
      return "redirect:/";
    }

    CartellaData data = CartellaData.builder()
        .stanza(StanzaDto.builder().idStanza(idStanza).build())
        .build();

    model.addAttribute("data", data);

    return "cartella/custom";
  }

  @GetMapping("/stanza/{idStanza}/cartella/{idTabella}")
  public String getTabella(Model model, @PathVariable("idStanza") String idStanza,
      @PathVariable("idTabella") String idTabella) {

    if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella,
        idStanza)) {
      return "redirect:/";
    }

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

    CartellaData data = CartellaData.builder()
        .stanza(tabelloneService.getStanza(idStanza))
        .numeroUscitoList(numeriUsciti)
        .vincitaList(tabelloneService.getVincite(idStanza))
        .premioCorrente(tabelloneService.getPremioCorrente(idStanza))
        .build();

    TabellaDto tabella = cartellaService.getTabella(idTabella, idStanza);
    tabella.setNumeriUsciti(numeriUsciti);
    data.setTabella(tabella);

    model.addAttribute("data", data);

    return "cartella/cartella";
  }

  @MessageMapping("/giocatore/{idStanza}")
  @SendTo("/partita/giocatore/{idStanza}")
  public MessaggioDto messaggi(MessaggioDto messaggioDto) {

    log.info("messaggioDto:" + messaggioDto.toString());
    if (messaggioDto.getAzione() != null && messaggioDto.getAzione() == 3) {
      if (messaggioDto.getIdPremio() != null) {
        messaggioDto.setNomePremio(PremioEnum.getValoreByCodice(messaggioDto.getIdPremio()));
      }
    }

    return messaggioDto;
  }
}
