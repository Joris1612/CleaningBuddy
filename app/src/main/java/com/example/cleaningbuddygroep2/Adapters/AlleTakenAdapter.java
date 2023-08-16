package com.example.cleaningbuddygroep2.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.Activities.AanpassenTaakActivity;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;

import java.util.List;

public class AlleTakenAdapter extends RecyclerView.Adapter<AlleTakenAdapter.ViewHolder> {
    private final List<Kamer> alleKamers;
    private final List<Taak> alleTaken;
    private final List<Gebruiker> alleGebruikers;
    private final Context context;

    public AlleTakenAdapter(List<Taak> taken, List<Gebruiker> gebruikers, List<Kamer> kamers, Context context) {
        //Attributen vullen
        this.alleTaken = taken;
        this.alleGebruikers = gebruikers;
        this.alleKamers = kamers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.alle_taken_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Taak taak = alleTaken.get(position);
        Gebruiker gebruiker = alleGebruikers.get(position);
        Kamer kamer = alleKamers.get(position);

        holder.taakNaamTf.setText(taak.getNaam());
        holder.toegewezenAanTf.setText(gebruiker.getGebruikersNaam());
        holder.kamerNaamTf.setText(kamer.getNaam());
        holder.itemView.setOnClickListener(v -> {

            Taak taak1 = alleTaken.get(position);
            Gebruiker gebruiker1 = alleGebruikers.get(position);
            Kamer kamer1 = alleKamers.get(position);

            //DataVersturenViaIntents
            Intent intent = new Intent(context, AanpassenTaakActivity.class);
            intent.putExtra("taakId", taak1.getId());
            intent.putExtra("taakNaam", taak1.getNaam());
            intent.putExtra("interval", taak1.getInterval());
            intent.putExtra("toegewezenAan", gebruiker1.getGebruikersNaam());
            intent.putExtra("kamerNaam", kamer1.getNaam());
            intent.putExtra("omschrijving", taak1.getOmschrijving());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return alleTaken.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taakNaamTf;
        private final TextView toegewezenAanTf;
        private final TextView kamerNaamTf;

        public ViewHolder(View itemView) {
            super(itemView);
            taakNaamTf = itemView.findViewById(R.id.alleTakenRow_taak_tf_id);
            toegewezenAanTf = itemView.findViewById(R.id.alleTakenRow_toegewezenAan_tf_id);
            kamerNaamTf = itemView.findViewById(R.id.alleTakenRow_kamer_tf_id);
        }
    }
}