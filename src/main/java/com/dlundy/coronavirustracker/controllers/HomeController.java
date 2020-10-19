package com.dlundy.coronavirustracker.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.dlundy.coronavirustracker.models.LocationStats;
import com.dlundy.coronavirustracker.services.CoronaVirusDataService;
import java.util.List;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;


    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();

        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getCases()).sum();
        int totalReportedDeaths = allStats.stream().mapToInt(stat -> stat.getDeaths()).sum();
        Date lastUpdated = coronaVirusDataService.getLastUpdated();
        String dataSourceUrl = coronaVirusDataService.getDataSourceURL();


        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalReportedDeaths", totalReportedDeaths);
        model.addAttribute("lastUpdated", lastUpdated);
        model.addAttribute("dataSourceUrl", dataSourceUrl);


        return "home";
    }
}

