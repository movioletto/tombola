package it.movioletto.web.cartella;

import it.movioletto.constant.PremioEnum;
import it.movioletto.dao.*;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.cartella.data.CartellaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cartella")
public class CartellaController {

	private final Logger logger = Logger.getLogger(CartellaController.class.getName());

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

		List<VincitaDao> vincitaList = tabelloneService.getVincite(idStanza);
		data.setVincitaList(vincitaList);
		PremioEnum premioCorrente = tabelloneService.getPremioCorrente(idStanza);
		data.setPremioCorrente(premioCorrente);

		model.addAttribute("data", data);

		return "cartella/cartella";
	}

	@MessageMapping("/giocatore/{idStanza}")
	@SendTo("/partita/giocatore/{idStanza}")
	public MessaggioDao messaggi(MessaggioDao messaggioDao) {

		logger.info("messaggioDao:" + messaggioDao.toString());
		if (messaggioDao.getAzione() != null && messaggioDao.getAzione() == 3) {
			if (messaggioDao.getIdPremio() != null) {
				messaggioDao.setNomePremio(PremioEnum.getValoreByCodice(messaggioDao.getIdPremio()));
			}
		}

		return messaggioDao;
	}
}
