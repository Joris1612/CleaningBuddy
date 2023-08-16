package com.example.cleaningbuddygroep2;

import com.example.cleaningbuddygroep2.Models.Validatie;

import org.junit.Assert;
import org.junit.Test;

public class LoginActivity_RegistrerenActivity_TestKlasse {

    @Test
    public void string_Moet_True_Returnen_Als_Gebruikersnaam_En_Wachtwoord_Voldoende_Zijn() {
        //Arrange
        String testGebruikersnaam = "Bryan";
        String testWachtwoord = "wachtwoord";

        //Act
        boolean resultaat = Validatie.isToegestaandeNaamEnWachtwoord(testGebruikersnaam,testWachtwoord);

        //Assert
        Assert.assertTrue(resultaat);
    }
    @Test
    public void string_Moet_False_Returnen_Als_Gebruiker_Nummers_Bevat() {
        //Arrange
        String testGebruikersnaam = "p0l0";
        String testWachtwoord = "wachtwoord";

        //Act
        boolean resultaat = Validatie.isToegestaandeNaamEnWachtwoord(testGebruikersnaam,testWachtwoord);

        //Assert
        Assert.assertFalse(resultaat);
    }

    @Test
    public void string_Moet_False_Returnen_Als_Gebruiker_SpecialeCharacter_Bevat() {
        //Arrange
        String testGebruikersnaam = "pl:::@@!@!";
        String testWachtwoord = "wachtwoord";

        //Act
        boolean resultaat = Validatie.isToegestaandeNaamEnWachtwoord(testGebruikersnaam,testWachtwoord);

        //Assert
        Assert.assertFalse(resultaat);
    }

    @Test
    public void string_Moet_False_Returnen_Als_Wachtwoord_Leeg_Is() {
        //Arrange
        String testGebruikersnaam = "Jaap";
        String testWachtwoord = "";

        //Act
        boolean resultaat = Validatie.isToegestaandeNaamEnWachtwoord(testGebruikersnaam,testWachtwoord);

        //Assert
        Assert.assertFalse(resultaat);
    }


    @Test
    public void strings_Moet_False_Returnen_Als_Gebruikersnaam_En_Wachtwoord_Leeg_Zijn() {
        //Arrange
        String testGebruikersnaam = "";
        String testWachtwoord = "";

        //Act
        boolean resultaat = Validatie.isToegestaandeNaamEnWachtwoord(testGebruikersnaam,testWachtwoord);

        //Assert
        Assert.assertFalse(resultaat);
    }

    @Test
    public void strings_Moet_True_Returnen_Als_Wachtwoord_Gelijk_Zijn() {
        //Arrange
        String testWachtwoord1 = "Test";
        String testWachtwoord2 = "Test";

        //Act
        boolean resultaat = Validatie.isZelfdeWachtwoord(testWachtwoord1,testWachtwoord2);

        //Assert
        Assert.assertTrue(resultaat);
    }

    @Test
    public void strings_Moet_False_Returnen_Als_Wachtwoord_Niet_Gelijk_Zijn() {
        //Arrange
        String testWachtwoord1 = "Jaap";
        String testWachtwoord2 = "Jelle";

        //Act
        boolean resultaat = Validatie.isZelfdeWachtwoord(testWachtwoord1,testWachtwoord2);

        //Assert
        Assert.assertFalse(resultaat);
    }



}
