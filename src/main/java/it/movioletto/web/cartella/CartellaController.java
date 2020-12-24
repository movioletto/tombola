package it.movioletto.web.cartella;

import it.movioletto.dao.MessaggioDao;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.dao.TabellaDao;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.cartella.data.CartellaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cartella")
public class CartellaController {

	@Autowired
	private CartellaService cartellaService;

	@Autowired
	private TabelloneService tabelloneService;

	@GetMapping("/new")
	public String getNew(Model model) {
		return "cartella/new";
	}

	@PostMapping("/newAct")
	public String getNewActPost(Model model, String idStanza) {
		if (!tabelloneService.existStanza(idStanza)) {
			return "redirect:/";
		}
		TabellaDao tabellaDao = cartellaService.creaTabella(idStanza);

		return "redirect:/cartella/stanza/" + tabellaDao.getIdStanza() + "/cartella/" + tabellaDao.getIdTabella();
	}

	@GetMapping("/new/{idStanza}")
	public String getNewAct(Model model, @PathVariable("idStanza") String idStanza) {
		if (!tabelloneService.existStanza(idStanza)) {
			return "redirect:/";
		}
		TabellaDao tabellaDao = cartellaService.creaTabella(idStanza);

		return "redirect:/cartella/stanza/" + tabellaDao.getIdStanza() + "/cartella/" + tabellaDao.getIdTabella();
	}

	@GetMapping("/stanza/{idStanza}/cartella/{idTabella}")
	public String getTabella(Model model, @PathVariable("idStanza") String idStanza, @PathVariable("idTabella") String idTabella) {

		if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella, idStanza)) {
			return "redirect:/";
		}

		CartellaData data = new CartellaData();

		StanzaDao stanzaDao = tabelloneService.getStanza(idStanza);
		data.setStanza(stanzaDao);

		List<NumeroUscitoDao> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);
		data.setNumeroUscitoList(numeriUsciti);

		TabellaDao tabella = cartellaService.getTabella(idTabella, idStanza);
		tabella.setNumeriUsciti(numeriUsciti);
		data.setTabella(tabella);

		model.addAttribute("data", data);

		return "cartella/cartella";
	}

	@MessageMapping("/giocatore/{idStanza}")
	@SendTo("/partita/giocatore/{idStanza}")
	public MessaggioDao saluto(MessaggioDao messaggioDao) {
		return messaggioDao;
	}
}
