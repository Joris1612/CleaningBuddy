package com.example.cleaningbuddygroep2.Activities;

import android.annotation.SuppressLint;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Adapters.VoltooideTakenAdapter;
import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.Models.VoltooideTaken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoltooideTakenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VoltooideTakenAdapter voltooideTakenAdapter;
    private List<VoltooideTaken> voltooideTaken = new ArrayList<>();
    private List<String> namen = new ArrayList<>();
    private List<Date> data = new ArrayList<>();
    private List<String> alleTakenNamen;
    private Boolean oplopend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // activity starten en koppelen aan de juiste views
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltooide_taken);
        recyclerView = findViewById(R.id.voltooideTaken_rv_id);
        // een toggle switch aanmaken zodat de taken oplopen of aflopen zijn
        oplopend = true;
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch toggle = findViewById(R.id.voltooideTaken_switchBtn_id);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                oplopend = isChecked;
            }
        });

        //Alle taken namen in een lijst stoppen.
        alleTakenNamen = Taak.alleTakenNamen(this);

        // taken spinner aanmaken en vullen met taken namen
        Spinner spinnerTaken = findViewById(R.id.voltooideTaken_taak_sp_id);
        ArrayAdapter<String> adapterTaken = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                alleTakenNamen);
        adapterTaken.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaken.setAdapter(adapterTaken);
        spinnerTaken.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // Alle history ophalen van de geselecteerde taak
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namen.clear();
                data.clear();
                String gekozenTaakNaam = parent.getItemAtPosition(position).toString();
                Integer taakId = Taak.selecteerTaakId(gekozenTaakNaam, parent.getContext());
                // History van alle voltooide taken ophalen uit de database van de geselecteerde taak (Oplopend of aflopend)
                if (oplopend) {
                    voltooideTaken = VoltooideTaken.voltooideTakenPerTaakOplopend(taakId, parent.getContext());
                    for (int i = 0; i < voltooideTaken.size(); i++) {
                        data.add(voltooideTaken.get(i).getDatum());
                        namen.add(Taak.naamZoekenPerId(voltooideTaken.get(i).getTaakId(), parent.getContext()));
                    }
                    voltooideTakenAdapter = new VoltooideTakenAdapter(namen, data);
                    recyclerView.setAdapter(voltooideTakenAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                } else {
                    voltooideTaken = VoltooideTaken.voltooideTakenPerTaakAflopend(taakId, parent.getContext());
                    for (int i = 0; i < voltooideTaken.size(); i++) {
                        data.add(voltooideTaken.get(i).getDatum());
                        namen.add(Taak.naamZoekenPerId(voltooideTaken.get(i).getTaakId(), parent.getContext()));
                    }
                    voltooideTakenAdapter = new VoltooideTakenAdapter(namen, data);
                    recyclerView.setAdapter(voltooideTakenAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Eerste taak uit de database gebruiken om de history lijst te vullen, als er nog niks geselcteerd is
                Integer taakId = 0;
                // History van alle voltooide taken ophalen uit de database van de geselecteerde taak (Oplopend of aflopend)
                if (oplopend) {
                    namen.clear();
                    data.clear();
                    voltooideTaken = VoltooideTaken.voltooideTakenPerTaakOplopend(taakId, parent.getContext());
                    System.out.println(voltooideTaken.size());
                    for (int i = 0; i < voltooideTaken.size(); i++) {
                        data.add(voltooideTaken.get(i).getDatum());
                        namen.add(Taak.naamZoekenPerId(voltooideTaken.get(i).getTaakId(), parent.getContext()));
                    }
                    voltooideTakenAdapter = new VoltooideTakenAdapter(namen, data);
                    recyclerView.setAdapter(voltooideTakenAdapter);
                } else {
                    voltooideTaken = VoltooideTaken.voltooideTakenPerTaakAflopend(taakId, parent.getContext());
                    for (int i = 0; i < voltooideTaken.size(); i++) {
                        data.add(voltooideTaken.get(i).getDatum());
                        namen.add(Database.getDatabase(parent.getContext()).taakDao().naamZoekenPerId(voltooideTaken.get(i).getTaakId()));
                    }
                    voltooideTakenAdapter = new VoltooideTakenAdapter(namen, data);
                    recyclerView.setAdapter(voltooideTakenAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
                }
            }
        });
    }
}
