package com.example.cleaningbuddygroep2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Adapters.alleKamersAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlleKamersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Kamer> kamers;
    private List<Integer> takenAantallen = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alle_kamers);
        // alle taken en alle kamers ophalen vanuit database
        kamers = Kamer.alleKamers(this);
        // voor alle kamers een lijst maken met hoeveel taken aan deze kamer gekoppeld zijn.
        for (int i = 0; i < kamers.size(); i++) {
            takenAantallen.add(Taak.aantalTakenPerKamer(kamers.get(i).getId(), this));
        }
        // Recycle view aanmaken en adapter koppelen
        recyclerView = findViewById(R.id.alleKamers_lijst_rv_id);
        alleKamersAdapter adapter = new alleKamersAdapter(kamers, takenAantallen, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}