package com.example.cleaningbuddygroep2.Models;

public class Validatie {

    public static boolean isToegestaandeNaamEnWachtwoord(String naam, String wachtwoord) {
        // Check if the string is empty
        if (naam.isEmpty() || naam == null || wachtwoord.isEmpty() || wachtwoord == null) {
            return false;
        }

        // Check if the string contains special characters or numbers
        for (char c : naam.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZelfdeWachtwoord(String wachtwoord, String wachtwoord2) {
        if (!wachtwoord.equals(wachtwoord2)) {
            return false;
        }
        return true;
    }

    public static boolean isNietLeeg(String naam, String wachtwoord) {
        if (naam.isEmpty() || naam == null || wachtwoord.isEmpty() || wachtwoord == null) {
            return false;
        }
        return true;
    }

    public static boolean generiekeAlleenLetters(String generiek) {
        for (char c : generiek.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean generiekIsNietLeeg(String generiek) {
        if (generiek.isEmpty() || generiek == null) {
            return false;
        }
        return true;
    }
}