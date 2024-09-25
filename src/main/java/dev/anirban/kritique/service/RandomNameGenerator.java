package dev.anirban.kritique.service;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class RandomNameGenerator {

    private static final String[] FIRST_NAMES = {
            "Luke", "Leia", "Han", "Obi-Wan", "Yoda", "Padmé", "Anakin", "Chewbacca",
            "Finn", "Rey", "Kylo", "Ahsoka", "Mace", "Lando", "Boba", "Jyn", "Darth",
            "C-3PO", "R2-D2", "Tarkin", "Wicket", "Jar Jar", "Plo Koon", "Qui-Gon",
            "Asajj", "Nien Nunb", "Hera", "Ezra", "Sabine", "Darth Maul", "Mon Mothma",
            "BB-8", "K-2SO", "Cassian", "Rose", "Jango", "L3-37", "Aayla", "Zeb",
            "Shmi", "Watto", "Greedo", "Rogue One", "Lumiya", "Revan", "Jaina",
            "Jacen", "Bastila", "Zorii", "Poe", "Leia Organa", "Darth Sidious",
            "Cad Bane", "Moff Gideon", "The Mandalorian", "Fives", "Echo",
            "Dengar", "Salacious Crumb", "Darth Nihilus", "Ahsoka Tano", "Bo-Katan",
            "Yaddle", "Rex", "Sifo-Dyas", "Mynock", "Maz Kanata", "Snoke",
            "Wampa", "Rancor", "Wicket W. Warrick", "Jabba", "Shmi Skywalker",
            "Kanan Jarrus", "Tion Medon", "Admiral Thrawn", "Count Dooku",
            "Lobot", "Lando Calrissian", "Nomi Sunrider", "Kira", "Ezra Bridger",
            "Chirrut", "Baze", "L3-37", "Vader", "Sabe", "Crix Madine",
            "Tarkin", "Dexter Jettster", "Aayla Secura", "Darth Bane",
            "Darth Plagueis", "Jedi Master", "Sith Lord", "Mace Windu",
            "Jan Dodonna", "Cody", "Fenn Rau", "Korr Sella", "Captain Rex"
    };

    private static final String[] LAST_NAMES = {
            "Skywalker", "Solo", "Kenobi", "Yoda", "Naberrie", "Djarin", "Organa",
            "Tano", "Palpatine", "Windu", "Fett", "Calrissian", "Jinn", "Dooku",
            "Grievous", "Tatooine", "Mandalorian", "Sith", "Revan", "Krennic",
            "Jakku", "Jedi", "First Order", "Resistance", "Clone", "Kaminoan",
            "Sullustan", "Togruta", "Wookiee", "Ewok", "Twilight", "Sarlacc",
            "Mustafar", "Naboo", "Endor", "Hoth", "Coruscant", "Dagobah",
            "Vader", "Hux", "Phasma", "Acklay", "Mothma", "Nightsister",
            "Mon Calamari", "Rattataki", "Bothan", "Alderaan", "Corellian",
            "Tatooinean", "Kaminoan", "Chiss", "Gungan", "Geonosian",
            "Sith Lord", "Jedi Knight", "Clone Trooper", "Death Star",
            "Imperial", "Rebel Alliance", "Resistance General", "Mandalorian",
            "Jedi Master", "Sith Apprentice", "Sarlacc Pit", "Droid",
            "Astromech", "Wookie", "Force-sensitive", "Starfighter Pilot",
            "Bounty Hunter", "HoloNet", "Twi'lek", "Rodian", "Yavin",
            "Jawa", "Nabooian", "Roonan", "Togrutan", "Mon Calamari",
            "Neimoidian", "Muun", "Gungan", "Zabrak", "Sullust",
            "Muunilinst", "Lothal", "Dantooine", "Geonosis", "Kashyyyk",
            "Kashyyykian", "Bespin", "Kamino", "Mandalore", "Ord Mantell",
            "Chandrila", "Felucia", "Kessel", "Endor", "Alderaan"
    };

    private final Random random;

    public RandomNameGenerator() {
        this.random = new Random();
    }

    public String generateRandomName() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }
}
