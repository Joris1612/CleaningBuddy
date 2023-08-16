package com.example.cleaningbuddygroep2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cleaningbuddygroep2.Models.Validatie;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.Adapters.takenPerKamerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TakenPerKamerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Kamer> kamers;
    private List<String> alleKamerNamen;
    private Kamer kamer;
    private List<Taak> alleTaken;
    private List<Gebruiker> takenGebruikers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_per_kamer);

        // Checken of gebruiker op de pagina beland is door middel van intent, of kamer id op 0 zetten als dit niet zo is
        Intent intent = getIntent();
        Integer kamerId = intent.getIntExtra("kamerId", 0);
        Button nieuweKnop = findViewById(R.id.takenPerKamer_nieuweKamer_btn_id);

        // alle kamer ophalen uit database, en een lijst maken van alle namen van kamers.
        kamers = Kamer.alleKamers(this);
        alleKamerNamen = Kamer.alleKamerNamen(this);

        // spinner vullen met alle kamer namen
        Spinner spinner = findViewById(R.id.takenPerKamer_alleKamer_spinner_id);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, alleKamerNamen);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        nieuweKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nieuweTaak = new Intent(TakenPerKamerActivity.this, NieuweTaakActivity.class);
                startActivity(nieuweTaak);
            }
        });

        if (kamerId == 0) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // kamer die gebruiker selecteert ophalen
                    String kamerNaam = (String) parent.getItemAtPosition(position);
                    Integer gekozenKamerId = Kamer.idOphalenOBVNaam(kamerNaam, parent.getContext());
                    kamer = Kamer.zoekenPerId(gekozenKamerId, parent.getContext());
                    // alle taken die gekoppeld zijn aan deze kamer ophalen
                    alleTaken = Taak.taakPerKamer(kamer.getId(), parent.getContext());
                    for (int i = 0; i < alleTaken.size(); i++) {
                        // gebruikers kiezen die bij elke taak horen
                        Integer gebruikerId = Taak.selecteerGebruikerId(alleTaken.get(i).getId(), parent.getContext());
                        takenGebruikers.add(Gebruiker.zoekenPerId(gebruikerId, parent.getContext()));
                    }
                    // recyclerView vullen met de kamer, taken en gebruikers.
                    recyclerView = findViewById(R.id.takenPerKamer_lijst_rv_id);
                    takenPerKamerAdapter adapter = new takenPerKamerAdapter(alleTaken, takenGebruikers, kamer, parent.getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Deze functie doet vrijwel hetzelfde als hierboven, alleen zorgt deze ervoor dat als er nog niks geselecteerd is vanuit de spinner,
                    // de recycle view de eerste kamer pakt uit de database en alles daarvan laat zien
                    Integer gekozenKamerId = Kamer.idOphalenOBVNaam(kamers.get(0).getNaam(), parent.getContext());
                    kamer = Kamer.zoekenPerId(gekozenKamerId, parent.getContext());
                    alleTaken = Taak.taakPerKamer(kamer.getId(), parent.getContext());
                    for (int i = 0; i < alleTaken.size(); i++) {
                        Integer gebruikerId = Taak.selecteerGebruikerId(alleTaken.get(i).getId(), parent.getContext());
                        takenGebruikers.add(Gebruiker.zoekenPerId(gebruikerId, parent.getContext()));
                    }
                    recyclerView = findViewById(R.id.takenPerKamer_lijst_rv_id);
                    takenPerKamerAdapter adapter = new takenPerKamerAdapter(alleTaken, takenGebruikers, kamer, parent.getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                }
            });
        } else {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // kamer die gebruiker selecteert ophalen
                    String kamerNaam = (String) parent.getItemAtPosition(position);
                    Integer gekozenKamerId = Kamer.idOphalenOBVNaam(kamerNaam, parent.getContext());
                    kamer = Kamer.zoekenPerId(gekozenKamerId, parent.getContext());
                    // alle taken die gekoppeld zijn aan deze kamer ophalen
                    alleTaken = Taak.taakPerKamer(kamer.getId(), parent.getContext());
                    for (int i = 0; i < alleTaken.size(); i++) {
                        // gebruikers kiezen die bij elke taak horen
                        Integer gebruikerId = Taak.selecteerGebruikerId(alleTaken.get(i).getId(), parent.getContext());
                        takenGebruikers.add(Gebruiker.zoekenPerId(gebruikerId, parent.getContext()));
                    }
                    // recyclerView vullen met de kamer, taken en gebruikers.
                    recyclerView = findViewById(R.id.takenPerKamer_lijst_rv_id);
                    takenPerKamerAdapter adapter = new takenPerKamerAdapter(alleTaken, takenGebruikers, kamer, parent.getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Deze functie doet vrijwel hetzelfde als hierboven, alleen zorgt deze ervoor dat als er nog niks geselecteerd is vanuit de spinner,
                    // de recycle view de eerste kamer pakt uit de database en alles daarvan laat zien
                    kamer = Kamer.zoekenPerId(kamerId, parent.getContext());
                    alleTaken = Taak.taakPerKamer(kamer.getId(), parent.getContext());
                    for (int i = 0; i < alleTaken.size(); i++) {
                        Integer gebruikerId = Taak.selecteerGebruikerId(alleTaken.get(i).getId(), parent.getContext());
                        takenGebruikers.add(Gebruiker.zoekenPerId(gebruikerId, parent.getContext()));
                    }
                    recyclerView = findViewById(R.id.takenPerKamer_lijst_rv_id);
                    takenPerKamerAdapter adapter = new takenPerKamerAdapter(alleTaken, takenGebruikers, kamer, parent.getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                }
            });
        }
    }


}