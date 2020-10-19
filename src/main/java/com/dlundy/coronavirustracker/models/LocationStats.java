package com.dlundy.coronavirustracker.models;

public class LocationStats {



    private String date;
    private String state;
    private String fips;
    private int cases;
    private int deaths;
    private String confirmedCases;
    private String confirmedDeaths;
    private String probableCases;
    private String probableDeaths;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(String confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public String getConfirmedDeaths() {
        return confirmedDeaths;
    }

    public void setConfirmedDeaths(String confirmedDeaths) {
        this.confirmedDeaths = confirmedDeaths;
    }

    public String getProbableCases() {
        return probableCases;
    }

    public void setProbableCases(String probableCases) {
        this.probableCases = probableCases;
    }

    public String getProbableDeaths() {
        return probableDeaths;
    }

    public void setProbableDeaths(String probableDeaths) {
        this.probableDeaths = probableDeaths;
    }


    @Override
    public String toString() {
        return "LocationStats{" +
                "date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", fips='" + fips + '\'' +
                ", cases='" + cases + '\'' +
                ", deaths='" + deaths + '\'' +
                ", confirmed_cases='" + confirmedCases + '\'' +
                ", confirmed_deaths='" + confirmedDeaths + '\'' +
                ", probable_cases='" + probableCases + '\'' +
                ", probable_deaths='" + probableDeaths + '\'' +
                '}';
    }
}



