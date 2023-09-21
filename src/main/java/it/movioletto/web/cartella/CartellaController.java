package it.movioletto.web.cartella;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.service.CartellaService;
import it.movioletto.service.CommonService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.cartella.data.CartellaData;
import it.movioletto.web.cartella.data.CartellaNewData;
import java.util.List;
import lombok.extern.java.Log;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cartella")
@Log
public class CartellaController {

  private final CartellaService cartellaService;

  private final CommonService commonService;

  private final TabelloneService tabelloneService;

  public CartellaController(CartellaService cartellaService, CommonService commonService,
      TabelloneService tabelloneService) {
    this.cartellaService = cartellaService;
    this.commonService = commonService;
    this.tabelloneService = tabelloneService;
  }

  @GetMapping("/new")
  public String getNew() {
    return "cartella/new";
  }

  @PostMapping("/newAct")
  public String getNewActPost(TabellaDto dto, RedirectAttributes redirectAttributes) {
    StanzaDto stanza = commonService.getStanza(dto.getCodiceStanza());

    if (stanza == null) {
      return "redirect:/";
    }

    if (!cartellaService.isValidaNuovaCartella(dto, stanza.getOpzioniStanza())) {
      redirectAttributes.addFlashAttribute("dto", dto);

      return "redirect:/cartella/new/redirect";
    }

    dto.setIdStanza(stanza.getIdStanza());
    TabellaDto tabellaDto = cartellaService.creaTabella(dto);

    return "redirect:/cartella/stanza/" + tabellaDto.getCodiceStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/new/{codiceStanza}")
  public String getNewAct(@PathVariable("codiceStanza") String codiceStanza, TabellaDto dto,
      RedirectAttributes redirectAttributes) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);

    if (stanza == null) {
      return "redirect:/";
    }

    if (!cartellaService.isValidaNuovaCartella(dto, stanza.getOpzioniStanza())) {
      if (StringUtils.isBlank(dto.getCodiceStanza())) {
        dto.setCodiceStanza(codiceStanza);
      }

      redirectAttributes.addFlashAttribute("dto", dto);

      return "redirect:/cartella/new/redirect";
    }

    dto.setIdStanza(stanza.getIdStanza());
    TabellaDto tabellaDto = cartellaService.creaTabella(dto);

    return "redirect:/cartella/stanza/" + tabellaDto.getCodiceStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/new/redirect")
  public String getNewRedirect(Model model, @ModelAttribute("dto") TabellaDto dto) {
    StanzaDto stanza = commonService.getStanza(dto.getCodiceStanza());

    if (stanza == null) {
      return "redirect:/";
    }

    CartellaNewData data = CartellaNewData.builder()
        .tabella(dto)
        .opzioniStanza(stanza.getOpzioniStanza())
        .animaleList(BooleanUtils.isTrue(stanza.getOpzioniStanza().getIconeTabella())
            ? commonService.getDieciAnimaleRandom() : null)
        .build();

    model.addAttribute("data", data);

    return "cartella/new";
  }

  @GetMapping("/custom")
  public String getCustom() {
    return "cartella/custom";
  }

  @PostMapping("/customAct")
  public String getCustomActPost(TabellaDto dto) {
    StanzaDto stanza = commonService.getStanza(dto.getCodiceStanza());

    if (stanza == null) {
      return "redirect:/";
    }
    dto.setIdStanza(stanza.getIdStanza());
    TabellaDto tabellaDto = cartellaService.creaTabella(dto);

    return "redirect:/cartella/stanza/" + tabellaDto.getCodiceStanza() + "/cartella/"
        + tabellaDto.getIdTabella();
  }

  @GetMapping("/custom/{codiceStanza}")
  public String getCustomAct(Model model, @PathVariable("codiceStanza") String codiceStanza) {
    if (!tabelloneService.existStanzaByCodice(codiceStanza)) {
      return "redirect:/";
    }

    CartellaData data = CartellaData.builder()
        .stanza(StanzaDto.builder().codice(codiceStanza).build())
        .build();

    model.addAttribute("data", data);

    return "cartella/custom";
  }

  @GetMapping("/stanza/{codiceStanza}/cartella/{idTabella}")
  public String getTabella(Model model, @PathVariable("codiceStanza") String codiceStanza,
      @PathVariable("idTabella") Integer idTabella) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);

    if (stanza == null || cartellaService.isNotExistTabella(idTabella, stanza.getIdStanza())) {
      return "redirect:/";
    }
    Integer idStanza = stanza.getIdStanza();

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

    CartellaData data = CartellaData.builder()
        .stanza(stanza)
        .numeroUscitoList(numeriUsciti)
        .vincitaList(tabelloneService.getVincite(idStanza, stanza.getOpzioniStanza()))
        .premioCorrente(tabelloneService.getPremioCorrente(idStanza, stanza.getOpzioniStanza()))
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
