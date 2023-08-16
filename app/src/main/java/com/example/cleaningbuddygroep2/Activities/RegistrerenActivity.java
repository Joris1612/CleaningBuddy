package com.example.cleaningbuddygroep2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cleaningbuddygroep2.Models.Validatie;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;

import java.util.List;

public class RegistrerenActivity extends AppCompatActivity {

    private List<Gebruiker> alleGebruikers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreren);
        alleGebruikers = Gebruiker.alleGebruikers(this);
    }

    public void registerAndGoToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);

        EditText gebruikersnaam = findViewById(R.id.registreren_gebruikersnaamET);
        EditText wachtwoord = findViewById(R.id.registreren_wachtwoordET);
        EditText herhaalWachtwoord = findViewById(R.id.registreren_herhaalwachtwoordET);

        String gebruikersnaamS = gebruikersnaam.getText().toString();
        String wachtwoordS = wachtwoord.getText().toString();
        String herhaalwachtwoordS = herhaalWachtwoord.getText().toString();

        boolean validatieGebruikersnaamEnWachtwoord = Validatie.isToegestaandeNaamEnWachtwoord(gebruikersnaamS, wachtwoordS);
        boolean validatieZelfdeWachtwoord = Validatie.isZelfdeWachtwoord(wachtwoordS, herhaalwachtwoordS);

        //Validatie Registreren:
        for (int i = 0; i <= alleGebruikers.size(); i++) {
            if (validatieGebruikersnaamEnWachtwoord && validatieZelfdeWachtwoord) {
                Gebruiker gebruiker = new Gebruiker(gebruikersnaamS, wachtwoordS);
                gebruiker.setId(gebruiker.getId());
                gebruiker.setGebruikersNaam(gebruikersnaamS);
                gebruiker.setWachtwoord(wachtwoordS);
                Gebruiker.gebruikerToevoegen(gebruiker, this);
                Toast.makeText(this, "Nieuwe gebruiker aangemaakt!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else if (!validatieGebruikersnaamEnWachtwoord) {
                gebruikersnaam.requestFocus();
                gebruikersnaam.setError(getText(R.string.setError_speciale_characters_leeg_text));
            } else if (!validatieZelfdeWachtwoord) {
                herhaalWachtwoord.requestFocus();
                herhaalWachtwoord.setError(getText(R.string.setError_herhaal_wachtwoord_request_text));
            } else if (gebruikersnaamS.equals(alleGebruikers.get(i).getGebruikersNaam())) {
                gebruikersnaam.requestFocus();
                gebruikersnaam.setError(getText(R.string.setError_gebruikersnaam_Zelfde_request_text));
            }
        }
    }
}