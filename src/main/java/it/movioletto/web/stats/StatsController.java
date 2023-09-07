package it.movioletto.web.stats;

import it.movioletto.service.TabelloneService;
import it.movioletto.web.stats.data.StatsData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

  private final TabelloneService tabelloneService;

  public StatsController(TabelloneService tabelloneService) {
    this.tabelloneService = tabelloneService;
  }

  @GetMapping("/")
  public String getHome(Model model) {
    StatsData data = StatsData.builder()
        .stanzaList(tabelloneService.getAllStanza())
        .build();

    model.addAttribute("data", data);
    return "stats/home";
  }

}
