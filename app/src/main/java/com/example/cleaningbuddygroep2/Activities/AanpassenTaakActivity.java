package com.example.cleaningbuddygroep2.Activities;


import static com.example.cleaningbuddygroep2.Models.Gebruiker.alleGebruikers;
import static com.example.cleaningbuddygroep2.Models.Kamer.alleKamers;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekIsNietLeeg;
import static com.example.cleaningbuddygroep2.Models.Validatie.generiekeAlleenLetters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;

import java.util.Date;
import java.util.List;

public class AanpassenTaakActivity extends AppCompatActivity {
    private final String[] interval = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "60", "120", "180", "360"};
    private EditText updateNaamET;
    private NumberPicker updateNumberPicker;
    private Spinner updateSpinnerToewijzenAan;
    private Spinner updateSpinnerKamer;
    private EditText updateOmschrijvingET;
    private Button updateButton;
    private List<Gebruiker> alleGebruikers;
    private List<Kamer> alleKamers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanpassen_taak);
        alleGebruikers = alleGebruikers(this);
        alleKamers = alleKamers(this);

        //VeldenBenoemen
        updateNaamET = findViewById(R.id.aanpassenTaak_naam_et_id);
        updateNumberPicker = findViewById(R.id.aanpassenTaak_interval_picker_id);
        updateOmschrijvingET = findViewById(R.id.aanpassenTaak_omschrijving_etml_id);
        updateButton = findViewById(R.id.aanpassenTaak_opslaan_btn_id);


        //Numberpicker
        updateNumberPicker = findViewById(R.id.aanpassenTaak_interval_picker_id);
        updateNumberPicker.setDisplayedValues(interval);
        updateNumberPicker.setMaxValue(interval.length - 1);
        updateNumberPicker.setMinValue(0);
        updateNumberPicker.setValue(0);

        //Gebruikerspinner
        Spinner spinnerGebruiker = findViewById(R.id.aanpassenTaak_toewijzenAan_spnr_id);
        ArrayAdapter<Gebruiker> adapterGebruiker = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                alleGebruikers(this));
        spinnerGebruiker.setAdapter(adapterGebruiker);

        //Kamerspinner
        Spinner spinnerKamer = findViewById(R.id.aanpassenTaak_kamer_spnr_id);
        ArrayAdapter<Kamer> adapterKamer = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                alleKamers(this));
        spinnerKamer.setAdapter(adapterKamer);

        //IntentsOntvangenUit
        Intent intent = getIntent();
        String taakNaam = intent.getStringExtra("taakNaam");
        String omschrijving = intent.getStringExtra("omschrijving");

        //SetOudeInformatieInVeldenViaIntents
        updateNaamET.setText(taakNaam);
        spinnerGebruiker.setAdapter(adapterGebruiker);
        updateOmschrijvingET.setText(omschrijving);
    }

    public void taakUpdaten(View view) {
        updateNaamET = findViewById(R.id.aanpassenTaak_naam_et_id);
        updateNumberPicker = findViewById(R.id.aanpassenTaak_interval_picker_id);
        updateSpinnerKamer = findViewById(R.id.aanpassenTaak_kamer_spnr_id);
        updateOmschrijvingET = findViewById(R.id.aanpassenTaak_omschrijving_etml_id);
        updateSpinnerToewijzenAan = findViewById(R.id.aanpassenTaak_toewijzenAan_spnr_id);

        //GebruikerSpinner
        String gebruiker = updateSpinnerToewijzenAan.getSelectedItem().toString();
        Integer gebruikerId = null;
        for (int i = 0; i < alleGebruikers.size(); i++) {
            if (gebruiker.equals(alleGebruikers.get(i).getGebruikersNaam())) {
                gebruikerId = alleGebruikers.get(i).getId();
            }
        }

        //KamerSpinner
        String kamer = updateSpinnerKamer.getSelectedItem().toString();
        Integer kamerId = null;
        for (int i = 0; i < alleKamers.size(); i++) {
            if (kamer.equals(alleKamers.get(i).getNaam())) {
                kamerId = alleKamers.get(i).getId();
            }
        }

        //TaakWaarden
        String numberPickerInt = interval[updateNumberPicker.getValue()];
        String naamString = updateNaamET.getText().toString();
        String omschrijvingString = updateOmschrijvingET.getText().toString();
        Date date = new Date();

        //IntentOphalenOmJuisteTaakTeUpdaten
        Intent intent = getIntent();
        Integer geselecteerdeTaakId = intent.getIntExtra("taakId", 0);

        //Validatie
        boolean leegVeldValidatieNaam = generiekIsNietLeeg(naamString);
        boolean bevatSpecialeTekensNaam = generiekeAlleenLetters(naamString);
        boolean leegVeldValidatieNaamOmschrijving = generiekIsNietLeeg(omschrijvingString);

        if (!leegVeldValidatieNaam) {
            updateNaamET.requestFocus();
            updateNaamET.setError(getText(R.string.setError_leegVeld_requestFocus_text));
        } else if (!bevatSpecialeTekensNaam) {
            updateNaamET.requestFocus();
            updateNaamET.setError(getText(R.string.setError_specialeTekens_requestFocus_text));
        } else if (!leegVeldValidatieNaamOmschrijving) {
            updateOmschrijvingET.requestFocus();
            updateOmschrijvingET.setError(getText(R.string.setError_leegVeld_requestFocus_text));
        } else {
            Taak taakUpdate = Database.getDatabase(this).taakDao().getTaaknaamViaId(geselecteerdeTaakId);
            taakUpdate.setId(geselecteerdeTaakId);
            taakUpdate.setNaam(naamString);
            taakUpdate.setInterval(Integer.parseInt(numberPickerInt));
            taakUpdate.setGebruikerId(gebruikerId);
            taakUpdate.setKamerId(kamerId);
            taakUpdate.setOmschrijving(omschrijvingString);
            taakUpdate.setAanmaakDatum(date);

            Database.getDatabase(this).taakDao().update(taakUpdate);
            Toast.makeText(this, R.string.alleTaken_updateGelukt_toast_text, Toast.LENGTH_SHORT).show();
            Intent menuIntent = new Intent(this, MenuActivity.class);
            startActivity(menuIntent);
            finish();
        }
    }

    public void finishActiviteit(View view) {
        Toast.makeText(this, R.string.aanpassenTaak_updateGeannuleerd_toast_text, Toast.LENGTH_SHORT).show();
        finish();
    }
}