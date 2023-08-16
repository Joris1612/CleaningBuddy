package com.example.cleaningbuddygroep2;

import static com.example.cleaningbuddygroep2.Models.Validatie.generiekIsNietLeeg;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekeAlleenLetters;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AanpassenTaakActivityTest {

    @Test
    public void naam_aanpassenTaak_moet_False_Returnen_Wanneer_String_Leeg_Is() {

        //ARRANGE
        String naam = "";

        //ACT
        Boolean resultaat = generiekIsNietLeeg(naam);

        //ASSERT
        Boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void naam_aanpassenTaak_moet_False_Returnen_Wanneer_String_niet_alleen_letters_bevat() {

        //ARRANGE
        String naam = "hallo5";

        //ACT
        Boolean resultaat = generiekeAlleenLetters(naam);

        //ASSERT
        Boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void omschrijving_aanpassenTaak_moet_False_Returnen_Wanneer_String_Leeg_Is() {

        //ARRANGE
        String omschrijving = "";

        //ACT
        Boolean resultaat = generiekIsNietLeeg(omschrijving);

        //ASSERT
        Boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void omschrijving_aanpassenTaak_moet_False_Returnen_Wanneer_String_niet_alleen_letters_bevat() {

        //ARRANGE
        String omschrijving = "0m5chr1jv1ng";

        //ACT
        Boolean resultaat = generiekeAlleenLetters(omschrijving);

        //ASSERT
        Boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }
}
