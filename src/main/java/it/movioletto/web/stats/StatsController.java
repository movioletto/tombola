package it.movioletto.web.stats;

import it.movioletto.dao.StanzaDao;
import it.movioletto.service.CartellaService;
import it.movioletto.service.TabelloneService;
import it.movioletto.web.stats.data.StatsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private CartellaService cartellaService;

	@Autowired
	private TabelloneService tabelloneService;

	@GetMapping("/")
	public String getHome(Model model) {
		StatsData data = new StatsData();

		List<StanzaDao> stanzaList = tabelloneService.getAllStanza();

		data.setStanzaList(stanzaList);

		model.addAttribute("data", data);
		return "stats/home";
	}

}
