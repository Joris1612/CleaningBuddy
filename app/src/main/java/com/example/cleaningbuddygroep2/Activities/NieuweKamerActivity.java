package com.example.cleaningbuddygroep2.Activities;

import static com.example.cleaningbuddygroep2.Models.Validatie.generiekIsNietLeeg;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekeAlleenLetters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Kamer;

public class NieuweKamerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nieuwe_kamer);
    }

    public void gaNaarMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Geen kamer aangemaakt!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void nieuweKamer(View view) {
        EditText naamKamer = findViewById(R.id.nieuweKamer_naam_et_id);

        String naamKamerString = naamKamer.getText().toString();

        boolean leegVeldValidatieNaam = generiekIsNietLeeg(naamKamerString);
        boolean bevatSpecialeTekensNaam = generiekeAlleenLetters(naamKamerString);

        if (!leegVeldValidatieNaam) {
            naamKamer.requestFocus();
            naamKamer.setError(getText(R.string.setError_leegVeld_requestFocus_text));
        } else if (!bevatSpecialeTekensNaam) {
            naamKamer.requestFocus();
            naamKamer.setError(getText(R.string.setError_specialeTekens_requestFocus_text));
        } else {
            Kamer kamer = new Kamer(naamKamerString);
            kamer.setId(kamer.getId());
            Kamer.kamerToevoegen(kamer, this);
            Toast.makeText(this, R.string.nieuweKamer_nieuweKamerAangemaakt_toast_text, Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}