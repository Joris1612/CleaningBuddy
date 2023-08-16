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

public class LogInActivity extends AppCompatActivity {
    private List<Gebruiker> alleGebruikers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        alleGebruikers = Gebruiker.alleGebruikers(this);
    }

    public void goToRegistreren(View view) {
        Intent intent = new Intent(this, RegistrerenActivity.class);
        startActivity(intent);
    }

    public void loginGoToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);

        EditText gebruikersnaam = findViewById(R.id.login_gebruikersnaam_id);
        EditText wachtwoord = findViewById(R.id.login_wachtwoord_id);

        String gebruikersnaamS = gebruikersnaam.getText().toString();
        String wachtwoordS = wachtwoord.getText().toString();

        boolean isNietLeeg = Validatie.isNietLeeg(gebruikersnaamS, wachtwoordS);

        // Validatie Login:
        boolean foundUser = false;
        for (int i = 0; i < alleGebruikers.size(); i++) {
            if (gebruikersnaamS.equals(alleGebruikers.get(i).getGebruikersNaam()) && wachtwoordS.equals(alleGebruikers.get(i).getWachtwoord())) {
                foundUser = true;
                break;
            }
        }

        if (foundUser) {
            Toast.makeText(this, R.string.login_succesvolIngelogd_toast_text, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else if (!isNietLeeg) {
            gebruikersnaam.requestFocus();
            wachtwoord.requestFocus();
            gebruikersnaam.setError(getText(R.string.setError_lege_velden_request_text));
            wachtwoord.setError(getText(R.string.setError_lege_velden_request_text));
        } else {
            gebruikersnaam.requestFocus();
            wachtwoord.requestFocus();
            gebruikersnaam.setError(getText(R.string.setError_verkeerde_gegevens_request_text));
            wachtwoord.setError(getText(R.string.setError_verkeerde_gegevens_request_text));
        }
    }
}