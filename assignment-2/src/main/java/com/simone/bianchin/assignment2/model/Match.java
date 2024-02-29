package com.simone.bianchin.assignment2.model;

import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Match implements Serializable {
    private String username;
    private ArrayList<Pair<String, String>> countries;
    private ArrayList<Pair<String, String>> countriesToGuess;
    private int points;
    private LocalDateTime localDate;

    public ArrayList<Pair<String, String>> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Pair<String, String>> countries) {
        this.countries = countries;
    }

    public ArrayList<Pair<String, String>> getCountriesToGuess() {
        return countriesToGuess;
    }

    public void setCountriesToGuess(ArrayList<Pair<String, String>> countriesToGuess) {
        this.countriesToGuess = countriesToGuess;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }

    public static Match generate(String username, ArrayList<Pair<String, String>> countries, ArrayList<Pair<String, String>> countriesToMatch) {
        Match match = new Match();
        match.username = username;
        match.localDate = LocalDateTime.now();
        match.setPoints(-1);
        match.setCountries(countries);
        match.setCountriesToGuess(countriesToMatch);
        return match;
    }
}
