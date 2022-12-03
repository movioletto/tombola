package it.movioletto.web.tabellone;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dto.MessaggioDto;
import it.movioletto.dto.NumeroUscitoDto;
import it.movioletto.dto.StanzaDto;
import it.movioletto.dto.TabellaDto;
import it.movioletto.dto.VincitaDto;
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

  public TabelloneController(TabelloneService tabelloneService) {
    this.tabelloneService = tabelloneService;
  }

  @GetMapping("/new")
  public String getNew() {
    return "tabellone/new";
  }

  @PostMapping("/newAct")
  public String getNewAct(String nome) {
    if (StringUtils.isBlank(nome)) {
      return "redirect:/tabellone/new";
    }

    StanzaDto stanzaDto = tabelloneService.creaStanza(StringUtils.abbreviate(nome, 200));

    return "redirect:/tabellone/stanza/" + stanzaDto.getIdStanza();
  }

  @GetMapping("/custom")
  public String getCustom() {
    return "tabellone/custom";
  }

  @PostMapping("/customAct")
  public String getCustomAct(String id, String nome) {
    if (StringUtils.isBlank(nome)) {
      return "redirect:/tabellone/custom";
    }
    StanzaDto stanzaDto = null;

    String idTemp = id = StringUtils.truncate(id, 20);

    int i = 0;
    while (tabelloneService.existStanza(idTemp)) {
      idTemp = StringUtils.truncate(id, 20 - StringUtils.length(String.valueOf(i))) + i++;
      if (i > 9) {
        stanzaDto = tabelloneService.creaStanza(StringUtils.abbreviate(nome, 200));
        break;
      }
    }

    if (stanzaDto == null) {
      stanzaDto = tabelloneService.creaStanza(idTemp, StringUtils.abbreviate(nome, 200));
    }

    return "redirect:/tabellone/stanza/" + stanzaDto.getIdStanza() + "/custom";
  }

  @GetMapping({"/stanza/{idStanza}", "/stanza/{idStanza}/{tipoPartita}"})
  public String getTabellone(Model model, @PathVariable("idStanza") String idStanza,
      @PathVariable(value = "tipoPartita", required = false) String tipoPartita) {

    if (!tabelloneService.existStanza(idStanza)) {
      return "redirect:/";
    }

    TabelloneData data = new TabelloneData();

    StanzaDto stanzaDto = tabelloneService.getStanza(idStanza);
    stanzaDto.setGiocatorePresenteList(tabelloneService.getGiocatoriPresenti(idStanza));
    data.setStanza(stanzaDto);

    List<NumeroUscitoDto> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);
    data.setNumeroUscitoList(numeriUsciti);

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
    data.setTabellaList(tabellaList);

    List<VincitaDto> vincitaList = tabelloneService.getVincite(idStanza);
    data.setVincitaList(vincitaList);
    PremioEnum premioCorrente = tabelloneService.getPremioCorrente(idStanza);
    data.setPremioCorrente(premioCorrente);

    data.setTipoPartita(tipoPartita);

    model.addAttribute("data", data);

    return "tabellone/tabellone";
  }

  @MessageMapping("/stanza/{idStanza}")
  @SendTo("/partita/stanza/{idStanza}")
  public MessaggioDto invioAggiornamentoNumeri(MessaggioDto messaggioDto) {
    return messaggioDto;
  }

}
