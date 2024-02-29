package com.simone.bianchin.assignment2.controllers;

import com.simone.bianchin.assignment2.model.Match;
import com.simone.bianchin.assignment2.model.User;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class MatchController {
    private ArrayList<Match> matches = new ArrayList<>();

    public MatchController() {
        this.deserializeState();
    }

    public synchronized Match recordMatch(Match match) {
        this.matches.add(match);
        return match;
    }

    public Match getMatch(LocalDateTime localDate, String username) {
        return matches.stream()
                .filter(item -> item.getLocalDate().equals(localDate) && item.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    public int getUserPoints(String username) {
        return matches.stream()
                .filter(item -> item.getUsername().equals(username) && item.getPoints() > 0)
                .map(Match::getPoints).reduce(0, Integer::sum);
    }

    public Match generateMatch(String username) {
        ArrayList<Pair<String, String>> countries = new ArrayList<>(Arrays.asList(
                new MutablePair<>("Algeria", "Algiers"),
                new MutablePair<>("Armenia", "Yerevan"),
                new MutablePair<>("Chad", "N'Djamena"),
                new MutablePair<>("Czech Republic", "Prague"),
                new MutablePair<>("Djibouti", "Djibouti"),
                new MutablePair<>("Gabon", "Libreville"),
                new MutablePair<>("Indonesia", "Jakarta"),
                new MutablePair<>("Lithuania", "Vilnius"),
                new MutablePair<>("Malta", "La Valletta"),
                new MutablePair<>("Ukraine", "Kiev")
        ));


        final int[] generatedIndexes = new Random().ints(0, countries.size()).distinct().limit(3).toArray();
        ArrayList<Pair<String, String>> countriesToGuess = new ArrayList<>();
        for (int index : generatedIndexes)
            countriesToGuess.add(countries.get(index));

        Match match = Match.generate(username, countries, countriesToGuess);
        this.recordMatch(match);
        return match;
    }

    private void deserializeState() {
        try
        {
            File f = new File("matchesData");
            if (!f.isFile() || !f.canRead()) return;

            FileInputStream fis = new FileInputStream("matchesData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.matches = (ArrayList<Match>) ois.readObject();

            System.out.println("===");
            System.out.println("Loaded matches from file:");
            for (Match user : matches) {
                System.out.println(user.getUsername() + " " + user.getPoints() + " " + user.getLocalDate());
            }

            ois.close();
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void serializeState() {
        try
        {
            FileOutputStream fos = new FileOutputStream("matchesData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.matches);
            oos.close();
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void destroy() {
        this.serializeState();
    }
}
