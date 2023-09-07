package it.movioletto.web.tabellone;

import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.dto.VincitaDto;
import it.movioletto.service.CommonService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.tabellone.data.TabelloneData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tabellone")
public class TabelloneController {

  private final TabelloneService tabelloneService;

  private final CommonService commonService;

  public TabelloneController(TabelloneService tabelloneService, CommonService commonService) {
    this.tabelloneService = tabelloneService;
    this.commonService = commonService;
  }

  @GetMapping("/new")
  public String getNew() {
    return "tabellone/new";
  }

  @PostMapping("/newAct")
  public String getNewAct(StanzaDto dto) {
    if (StringUtils.isBlank(dto.getNome())) {
      return "redirect:/tabellone/new";
    }

    StanzaDto stanzaDto = tabelloneService.creaStanza(dto);

    return "redirect:/tabellone/stanza/" + stanzaDto.getCodice();
  }

  @GetMapping("/custom")
  public String getCustom() {
    return "tabellone/custom";
  }

  @PostMapping("/customAct")
  public String getCustomAct(StanzaDto dto) {
    if (StringUtils.isBlank(dto.getNome())) {
      return "redirect:/tabellone/custom";
    }

    StanzaDto stanzaDto = tabelloneService.creaStanza(dto);

    return "redirect:/tabellone/stanza/" + stanzaDto.getIdStanza() + "/custom";
  }

  @GetMapping({"/stanza/{codiceStanza}", "/stanza/{codiceStanza}/{tipoPartita}"})
  public String getTabellone(Model model, @PathVariable("codiceStanza") String codiceStanza,
      @PathVariable(value = "tipoPartita", required = false) String tipoPartita) {
    StanzaDto stanza = commonService.getStanza(codiceStanza);

    if (stanza == null) {
      return "redirect:/";
    }
    Integer idStanza = stanza.getIdStanza();

    TabelloneData data = new TabelloneData();

    stanza.setGiocatorePresenteList(tabelloneService.getGiocatoriPresenti(idStanza));
    data.setStanza(stanza);

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);
    data.setNumeroUscitoList(numeriUsciti);

    List<TabellaDto> tabellaList = this.getSequenzaTabellone(numeriUsciti);
    data.setTabellaList(tabellaList);

    List<VincitaDto> vincitaList = tabelloneService.getVincite(idStanza, stanza.getOpzioniStanza());
    data.setVincitaList(vincitaList);
    data.setPremioCorrente(tabelloneService.getPremioCorrente(idStanza, stanza.getOpzioniStanza()));

    data.setTipoPartita(tipoPartita);

    model.addAttribute("data", data);

    return "tabellone/tabellone";
  }

  private List<TabellaDto> getSequenzaTabellone(List<NumeroUscitoDto> numeriUsciti) {
    List<String> sequenzeTabellone = new ArrayList<>(
        Arrays.asList("1,2,3,4,5|11,12,13,14,15|21,22,23,24,25",
            "6,7,8,9,10|16,17,18,19,20|26,27,28,29,30",
            "31,32,33,34,35|41,42,43,44,45|51,52,53,54,55",
            "36,37,38,39,40|46,47,48,49,50|56,57,58,59,60",
            "61,62,63,64,65|71,72,73,74,75|81,82,83,84,85",
            "66,67,68,69,70|76,77,78,79,80|86,87,88,89,90"));

    List<TabellaDto> tabellaList = new ArrayList<>();
    sequenzeTabellone.forEach(sequenza -> tabellaList.add(new TabellaDto(sequenza)));
    tabellaList.forEach(tabella -> tabella.setNumeriUsciti(numeriUsciti));
    return tabellaList;
  }

  @MessageMapping("/stanza/{idStanza}")
  @SendTo("/partita/stanza/{idStanza}")
  public MessaggioDto invioAggiornamentoNumeri(MessaggioDto messaggioDto) {
    return messaggioDto;
  }

}
