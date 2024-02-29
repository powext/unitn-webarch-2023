package com.example.assignment3.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NicknameGenerator {
    public static String generate() {
        ArrayList<String> nicknames = new ArrayList<>(List.of("Peanut", "Apatosaurus", "Riffraff", "Rusty", "Crackers", "Ruby", "Boomer", "Juba", "Bloodymuzzle", "Tyrannos", "Troodon", "Respighi", "Mosa", "Cheese Puns", "Lystrosaurus", "Render", "Stegmutt", "Tyranno", "Spiny", "D-Structs", "Music Puns", "Zippo", "Bowlingball", "Zuvan", "Grimlock", "Rusty", "Brand", "Spinosaurus", "Extinction", "Stegosaurus", "Elsa", "Flower Puns", "Ptera", "Spielberg", "Christmas Puns", "Chaos", "Ducky", "Elephant Puns", "Protoceratops", "Petrie", "The King", "Rolo", "Noosie", "Albinosaur", "Tyr", "Longneck", "Sad Puns", "Ruban", "Pasta Puns", "Brachiosaurus", "Pterodactyl", "Coffee Puns", "Grumpy", "Ray", "Sneaker", "IDK", "Lizzy", "Dodo Male", "Johari", "Sneaker", "Bigboss", "Raymond", "Fossil", "Haxx", "Animal Puns", "Sunshine", "Dingo", "Fran", "Tea", "Bronto Female"));
        Random random = new Random();
        return nicknames.get(random.nextInt(nicknames.size()));
    }
}
