package dev.anirban.kritique.service;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProfanityService {

    // Set of profane words for quick lookup
    private static final Set<String> PROFANE_WORDS = new HashSet<>(Arrays.asList(
            "fakk", "fuck", "fuk", "nigger", "shit", "bitch", "ass", "dick", "cunt",
            "piss", "crap", "whore", "slut", "dumbass", "bastard", "twat", "prick"
    ));


    // Method to check if the input contains profane words
    public boolean containsProfanity(String input) {
        String[] words = input.toLowerCase().split("\\s+");

        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            if (PROFANE_WORDS.contains(cleanWord))
                return true;
        }
        return false;
    }
}


