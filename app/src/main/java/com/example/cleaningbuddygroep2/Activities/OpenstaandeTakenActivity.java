package com.example.cleaningbuddygroep2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cleaningbuddygroep2.Models.VoltooideTaken;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.Adapters.openstaandeTakenAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpenstaandeTakenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Kamer> kamers = new ArrayList<>();
    private List<Taak> alleTaken;
    private List<Gebruiker> gebruikers = new ArrayList<>();
    private List<Taak> openstaandeTaken = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // activity starten en koppelen aan de view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openstaande_taken);
        // huidige datum ophalen en alle taken ophalen
        Date vandaag = new Date();
        alleTaken = Taak.alleTaken(this);
        for (Taak taak : alleTaken) {
            // kijken of de taak al in de history staat
            if (VoltooideTaken.taakIsInHistory(taak.getId(), this)) {
                // meest recente voltooide taak ophalen vanuit de history
                VoltooideTaken voltooideTaak = VoltooideTaken.meestRecenteVoltooideTaak(taak.getId(), this);
                // checken of de interval al is verstreken
                long timeDifference = vandaag.getTime() - voltooideTaak.getDatum().getTime();
                if ((timeDifference / (1000 * 60 * 60 * 24)) % 365 >= taak.getInterval()) {
                    // openstaande taak, gebruiker en kamer ophalen voor het vullen van de view
                    openstaandeTaken.add(taak);
                    Integer gebruikerId = taak.getGebruikerId();
                    Gebruiker gebruiker = Gebruiker.zoekenPerId(gebruikerId, this);
                    gebruikers.add(gebruiker);
                    Integer kamerId = taak.getKamerId();
                    Kamer kamer = Kamer.zoekenPerId(kamerId, this);
                    kamers.add(kamer);
                }
            }

            // als de taak nieuw is wordt die meteen toegevoegd aan de openstaande taken
            else {
                // openstaande taak, gebruiker en kamer ophalen voor het vullen van de view
                openstaandeTaken.add(taak);
                Integer gebruikerId = taak.getGebruikerId();
                Gebruiker gebruiker = Gebruiker.zoekenPerId(gebruikerId, this);
                gebruikers.add(gebruiker);
                Integer kamerId = taak.getKamerId();
                Kamer kamer = Kamer.zoekenPerId(kamerId, this);
                kamers.add(kamer);
            }
        }

        // Recyclyer view aanmaken en koppelen aan de adapter
        recyclerView = findViewById(R.id.openstaandeTaken_lijst_rv_id);
        openstaandeTakenAdapter adapter = new openstaandeTakenAdapter(openstaandeTaken, gebruikers, kamers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}