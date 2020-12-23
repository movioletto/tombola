package it.movioletto.web.tabellone;

import it.movioletto.dao.MessaggioDao;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.service.TabelloneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tabellone")
public class TabelloneRestController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private TabelloneService tabelloneService;

	public TabelloneRestController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@GetMapping("/stanza/{idStanza}/numero")
	public MessaggioDao getNumeroTabellone(@PathVariable("idStanza") String idStanza, RedirectAttributes redirectAttributes) {

		if (!tabelloneService.existStanza(idStanza)) {
			return null;
		}

		List<NumeroUscitoDao> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

		Random random = new Random();
		int numeroEstratto;
		do {
			numeroEstratto = random.nextInt(90) + 1;
		} while (numeriUsciti.contains(new NumeroUscitoDao(numeroEstratto)));

		tabelloneService.saveNumeroEstratto(idStanza, numeroEstratto);

		simpMessagingTemplate.convertAndSend("/partita/stanza/" + idStanza, new MessaggioDao(numeroEstratto));

		return new MessaggioDao(numeroEstratto);
	}

}
