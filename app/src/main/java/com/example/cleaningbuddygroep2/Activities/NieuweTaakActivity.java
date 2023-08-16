package com.example.cleaningbuddygroep2.Activities;

import static com.example.cleaningbuddygroep2.Models.Gebruiker.alleGebruikers;
import static com.example.cleaningbuddygroep2.Models.Kamer.alleKamers;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekIsNietLeeg;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekeAlleenLetters;


import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class NieuweTaakActivity extends AppCompatActivity {
    private final String[] interval = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "60", "120", "180", "360"};
    private EditText naamET;
    private NumberPicker numberPicker;
    private Spinner spinnerToewijzenAan;
    private Spinner spinnerKamer;
    private EditText omschrijvingET;

    private ActivityResultLauncher<Intent> launcher;
    private List<Gebruiker> alleGebruikers;
    private List<Kamer> alleKamers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nieuwe_taak);
        alleGebruikers = alleGebruikers(this);
        alleKamers = alleKamers(this);

        //Numberpicker
        numberPicker = findViewById(R.id.nieuweTaak_interval_picker_id);
        numberPicker.setDisplayedValues(interval);
        numberPicker.setMaxValue(interval.length - 1);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);

        //Gebruikerspinner
        Spinner toewijzenAanSpinner = findViewById(R.id.nieuweTaak_toewijzenAan_spnr_id);

        ArrayAdapter<Gebruiker> toewijzenAanAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                alleGebruikers(this));

        toewijzenAanSpinner.setAdapter(toewijzenAanAdapter);

        //Kamerspinner
        Spinner kamerSpinner = findViewById(R.id.nieuweTaak_kamer_spnr_id);

        ArrayAdapter<Kamer> kamerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                alleKamers(this));

        kamerSpinner.setAdapter(kamerAdapter);
    }

    public void taakToevoegen(View view) throws ParseException {
        naamET = findViewById(R.id.nieuweTaak_naam_et_id);
        numberPicker = findViewById(R.id.nieuweTaak_interval_picker_id);
        spinnerToewijzenAan = findViewById(R.id.nieuweTaak_toewijzenAan_spnr_id);
        spinnerKamer = findViewById(R.id.nieuweTaak_kamer_spnr_id);
        omschrijvingET = findViewById(R.id.nieuweTaak_omschrijving_etml_id);

        //Gebruikerspinner
        String gebruiker = spinnerToewijzenAan.getSelectedItem().toString();

        Integer gebruikerId = null;
        for (int i = 0; i < alleGebruikers.size(); i++) {
            if (gebruiker.equals(alleGebruikers.get(i).getGebruikersNaam())) {
                gebruikerId = alleGebruikers.get(i).getId();
            }
        }

        //Kamerspinner
        String kamer = spinnerKamer.getSelectedItem().toString();
        Integer kamerId = null;
        for (int i = 0; i < alleKamers.size(); i++) {
            if (kamer.equals(alleKamers.get(i).getNaam())) {
                kamerId = alleKamers.get(i).getId();
            }
        }

        String naamString = naamET.getText().toString();
        String numberPickerInt = interval[numberPicker.getValue()];
        String omschrijvingString = omschrijvingET.getText().toString();
        Date date = new Date();

        //Validatie
        boolean leegVeldValidatie = generiekIsNietLeeg(naamString);
        boolean bevatSpecialeTekens = generiekeAlleenLetters(naamString);
        boolean leegVeldValidatieOmschrijving = generiekIsNietLeeg(omschrijvingString);

        if (!leegVeldValidatie) {
            naamET.requestFocus();
            naamET.setError(getText(R.string.setError_leegVeld_requestFocus_text));
        } else if (!bevatSpecialeTekens) {
            naamET.requestFocus();
            naamET.setError(getText(R.string.setError_specialeTekens_requestFocus_text));
        } else if (!leegVeldValidatieOmschrijving) {
            omschrijvingET.requestFocus();
            omschrijvingET.setError(getText(R.string.setError_leegVeld_requestFocus_text));
        } else {
            Taak taak = new Taak(naamString, Integer.parseInt(numberPickerInt), gebruikerId, kamerId, omschrijvingString, date);
            Taak.taakToevoegen(taak, this);
            Toast.makeText(this, R.string.alleTaken_nieuweTaakAangemaakt_toast_text, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void finishActiviteit(View view) {
        Toast.makeText(this, R.string.nieuweTaak_geenTaakAangemaakt_toast_text, Toast.LENGTH_SHORT).show();
        finish();
    }
}