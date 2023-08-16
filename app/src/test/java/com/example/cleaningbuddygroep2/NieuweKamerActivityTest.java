package com.example.cleaningbuddygroep2;

import static com.example.cleaningbuddygroep2.Models.Validatie.generiekIsNietLeeg;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekeAlleenLetters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NieuweKamerActivityTest {
    @Test
    public void moet_False_Returnen_Wanneer_String_Leeg_Is() {

        //ARRANGE
        String waarde = "";

        //ACT
        Boolean resultaat = generiekIsNietLeeg(waarde);

        //ASSERT
        Boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void moet_True_Returnen_Wanneer_String_Gevuld_Is() {

        //ARRANGE
        String waarde = "Test";

        //ACT
        Boolean resultaat = generiekIsNietLeeg(waarde);

        //ASSERT
        Boolean verwacht = true;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void moet_True_Returnen_Wanneer_String_Letters_Bevat() {

        //ARRANGE
        String waarde = "Test";

        //ACT
        Boolean resultaat = generiekeAlleenLetters(waarde);

        //ASSERT
        boolean verwacht = true;
        assertEquals(verwacht, resultaat);
    }

    @Test
    public void moet_False_Returnen_Wanneer_String_Geen_Letters_Bevat() {

        //ARRANGE
        String waarde = "T3st";

        //ACT
        Boolean resultaat = generiekeAlleenLetters(waarde);

        //ASSERT
        boolean verwacht = false;
        assertEquals(verwacht, resultaat);
    }
}
