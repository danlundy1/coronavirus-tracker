
package com.dlundy.coronavirustracker.services;
import com.dlundy.coronavirustracker.models.LocationStats;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-states.csv";

    private List<LocationStats> allStats = new ArrayList<>();
    private Date lastUpdated;

    public List<LocationStats> getAllStats() {
        return allStats;
    }
    public Date getLastUpdated() { return lastUpdated; }
    public String getDataSourceURL() { return VIRUS_DATA_URL;}

    @PostConstruct
    @Scheduled(fixedRate = 14400000)
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {


            LocationStats locationStat = new LocationStats();
            locationStat.setDate(record.get("date"));
            locationStat.setState(record.get("state"));
            locationStat.setFips(record.get("fips"));
            int latestCases = Integer.parseInt(record.get(record.size() - 6));
            locationStat.setCases(latestCases);
            int latestDeaths = Integer.parseInt(record.get(record.size() - 5));
            locationStat.setDeaths(latestDeaths);
            locationStat.setConfirmedCases(record.get("confirmed_cases"));
            if (Objects.equals(locationStat.getConfirmedCases(), "")) {
                locationStat.setConfirmedCases("0");
            }
            locationStat.setConfirmedDeaths(record.get("confirmed_deaths"));
            if (Objects.equals(locationStat.getConfirmedDeaths(), "")) {
                locationStat.setConfirmedDeaths("0");
            }
            locationStat.setProbableCases(record.get("probable_cases"));
            if (Objects.equals(locationStat.getProbableCases(), "")) {
                locationStat.setProbableCases("0");
            }
            locationStat.setProbableDeaths(record.get("probable_deaths"));
            if (Objects.equals(locationStat.getProbableDeaths(), "")) {
                locationStat.setProbableDeaths("0");
            }
            newStats.add(locationStat);

        }
        this.allStats = newStats;
        lastUpdated = new Date();

    }


        }



