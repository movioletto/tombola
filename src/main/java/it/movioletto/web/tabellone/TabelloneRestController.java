package it.movioletto.web.tabellone;

import it.movioletto.constant.AzioneEnum;
import it.movioletto.constant.PremioEnum;
import it.movioletto.dao.MessaggioDao;
import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.TabellaDao;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tabellone")
public class TabelloneRestController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private TabelloneService tabelloneService;

	@Autowired
	private CartellaService cartellaService;

	public TabelloneRestController(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@GetMapping("/stanza/{idStanza}/numero")
	public MessaggioDao getNumeroTabellone(@PathVariable("idStanza") String idStanza) {

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

		MessaggioDao messaggioDao = new MessaggioDao();
		messaggioDao.setAzione(AzioneEnum.NUMERO_USCITO.getCodice());
		messaggioDao.setNumeroUscito(numeroEstratto);

		simpMessagingTemplate.convertAndSend("/partita/stanza/" + idStanza, messaggioDao);

		return messaggioDao;
	}

	@GetMapping("/stanza/{idStanza}/tabella/{idTabella}")
	public TabellaDao getTabellaStanza(@PathVariable("idStanza") String idStanza, @PathVariable("idTabella") String idTabella) {

		if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella, idStanza)) {
			return null;
		}

		List<NumeroUscitoDao> numeriUsciti = tabelloneService.getNumeriUsciti(idStanza);

		TabellaDao tabella = cartellaService.getTabella(idTabella, idStanza);
		tabella.setNumeriUsciti(numeriUsciti);

		return tabella;
	}

	@GetMapping("/stanza/{idStanza}/premioCorrente")
	public MessaggioDao getPremioCorrente(@PathVariable("idStanza") String idStanza) {

		if (!tabelloneService.existStanza(idStanza)) {
			return null;
		}

		PremioEnum premioCorrente = tabelloneService.getPremioCorrente(idStanza);

		if (premioCorrente == null) {
			return null;
		}

		MessaggioDao messaggioDao = new MessaggioDao();
		messaggioDao.setIdPremio(premioCorrente.getCodice());
		messaggioDao.setNomePremio(premioCorrente.getValore());
		return messaggioDao;
	}

	@GetMapping("/stanza/{idStanza}/premio/{idTabella}/{idPremio}")
	public MessaggioDao savePremio(@PathVariable("idStanza") String idStanza, @PathVariable("idTabella") String idTabella, @PathVariable("idPremio") Integer idPremio) {

		if (!tabelloneService.existStanza(idStanza) || !cartellaService.existTabella(idTabella, idStanza) || tabelloneService.existPremio(idStanza, idTabella, idPremio)) {
			return null;
		}

		tabelloneService.savePremio(idStanza, idTabella, idPremio);

		MessaggioDao messaggioDao = new MessaggioDao();
		messaggioDao.setAzione(4);
		messaggioDao.setIdTabella(idTabella);
		messaggioDao.setNomePremio(PremioEnum.getValoreByCodice(idPremio));
		messaggioDao.setIdPremio(idPremio);

		return messaggioDao;
	}

}
