package it.movioletto.web.tabellone;

import it.movioletto.dao.MessaggioDao;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.tabellone.data.TabelloneData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/tabellone")
public class TabelloneController {

	private final SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private TabelloneService tabelloneService;

	public TabelloneController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@GetMapping("/new")
	public String getNew(Model model) {
		return "tabellone/new";
	}

	@PostMapping("/newAct")
	public String getNewAct(Model model, String nome) {

		StanzaDao stanzaDao = tabelloneService.creaStanza(nome);

		return "redirect:/tabellone/stanza/" + stanzaDao.getIdStanza();
	}

	@GetMapping("/stanza/{idStanza}")
	public String getTabellone(Model model, @PathVariable("idStanza") String idStanza, HttpServletRequest request) {

		if (!tabelloneService.existStanza(idStanza)) {
			return "redirect:/";
		}

		TabelloneData data = new TabelloneData();

		StanzaDao stanzaDao = tabelloneService.getStanza(idStanza);
		data.setStanza(stanzaDao);

		List<NumeroUscitoDao> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);
		data.setNumeroUscitoList(numeriUsciti);

		List<String> sequenzeTabellone = new ArrayList<>(
				Arrays.asList("1,2,3,4,5|11,12,13,14,15|21,22,23,24,25", "6,7,8,9,10|16,17,18,19,20|26,27,28,29,30",
						"31,32,33,34,35|41,42,43,44,45|51,52,53,54,55", "36,37,38,39,40|46,47,48,49,50|56,57,58,59,60",
						"61,62,63,64,65|71,72,73,74,75|81,82,83,84,85", "66,67,68,69,70|76,77,78,79,80|86,87,88,89,90"));

		List<TabellaDao> tabellaList = new ArrayList<>();
		sequenzeTabellone.forEach(sequenza -> tabellaList.add(new TabellaDao(sequenza)));
		tabellaList.forEach(tabella -> tabella.setNumeriUsciti(numeriUsciti));
		data.setTabellaList(tabellaList);

		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null && CollectionUtils.contains(inputFlashMap.keySet().iterator(), "numeroEstratto")) {
			data.setNumeroEstratto((Integer) inputFlashMap.get("numeroEstratto"));
		}

		model.addAttribute("data", data);

		return "tabellone/tabellone";
	}

	@GetMapping("/stanza/{idStanza}/numero")
	public RedirectView getNumeroTabellone(@PathVariable("idStanza") String idStanza, RedirectAttributes redirectAttributes) {

		if (!tabelloneService.existStanza(idStanza)) {
			return new RedirectView("/");
		}

		List<NumeroUscitoDao> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

		Random random = new Random();
		int numeroEstratto;
		do {
			numeroEstratto = random.nextInt(90) + 1;
		} while (numeriUsciti.contains(new NumeroUscitoDao(numeroEstratto)));

		tabelloneService.saveNumeroEstratto(idStanza, numeroEstratto);

		simpMessagingTemplate.convertAndSend("/partita/stanza/" + idStanza, new MessaggioDao(numeroEstratto));

		redirectAttributes.addFlashAttribute("numeroEstratto", numeroEstratto);
		return new RedirectView("/tombola/tabellone/stanza/" + idStanza);
	}

	@MessageMapping("/stanza/{idStanza}")
	@SendTo("/partita/stanza/{idStanza}")
	public MessaggioDao simple(@DestinationVariable String idStanza, MessaggioDao messaggioDao) {
		return messaggioDao;
	}
}
