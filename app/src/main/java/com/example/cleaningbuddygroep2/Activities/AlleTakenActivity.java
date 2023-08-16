package com.example.cleaningbuddygroep2.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Adapters.AlleTakenAdapter;
import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlleTakenActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private List<Kamer> kamers = new ArrayList<>();
    private List<Taak> alleTaken;
    private List<Gebruiker> gebruikers = new ArrayList<>();

    public void gaNaarNieuwTaak (View view){
        Intent intent = new Intent(this, NieuweTaakActivity.class);
        startActivity(intent);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alle_taken);

        alleTaken = Database.getDatabase(this).taakDao().alleTaken();
        for (Taak taak : alleTaken) {
            Integer gebruikerId = taak.getGebruikerId();
            Gebruiker gebruiker = Database.getDatabase(this).gebruikerDao().zoekenPerId(gebruikerId);
            gebruikers.add(gebruiker);
            Integer kamerId = taak.getKamerId();
            Kamer kamer = Database.getDatabase(this).kamerDao().zoekenPerId(kamerId);
            kamers.add(kamer);

            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                    }
            );
        }

        //Recyclerview linken aan database
        recyclerView = findViewById(R.id.alleTaken_alleTakenLijst_rv_id);
        AlleTakenAdapter adapter = new AlleTakenAdapter(alleTaken, gebruikers, kamers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}