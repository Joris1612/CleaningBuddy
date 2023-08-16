package com.example.cleaningbuddygroep2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.Activities.VoltooideTakenActivity;
import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.Models.VoltooideTaken;

import java.util.Date;
import java.util.List;

public class openstaandeTakenAdapter extends RecyclerView.Adapter<openstaandeTakenAdapter.ViewHolder> {
    private List<Taak> taken;
    private List<Gebruiker> gebruikers;
    private List<Kamer> kamers;
    private Context context;

    public openstaandeTakenAdapter(List<Taak> taken, List<Gebruiker> gebruikers, List<Kamer> kamers, Context context) {
        // attributen vullen vanuit de activity
        this.taken = taken;
        this.gebruikers = gebruikers;
        this.kamers = kamers;
        this.context = context;
    }

    // viewholder aanmaken
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.openstaande_taken_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // alle informatie uit de lijsten halen op basis van de positie in de lijst.
        Taak taak = taken.get(position);
        Gebruiker gebruiker = gebruikers.get(position);
        Kamer kamer = kamers.get(position);
        holder.taakTf.setText(taak.getNaam());
        holder.gebruikerTf.setText(gebruiker.getGebruikersNaam());
        holder.kamerTf.setText(kamer.getNaam());

        // taak afvinken
        holder.afvinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afvinken(position);
            }
        });

        holder.meerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VoltooideTakenActivity.class);
                context.startActivity(intent);
            }
        });
    }

    // functie om een taak af te vinken, en in te voegen in de voltooide taken database
    @SuppressLint("NotifyDataSetChanged")
    private void afvinken(int position) {
        Taak afgevinkteTaak = taken.get(position);
        VoltooideTaken voltooideTaak = new VoltooideTaken(afgevinkteTaak.getNaam(), new Date(), gebruikers.get(position).getId(), taken.get(position).getId());
        VoltooideTaken.voltooideTaakToevoegen(voltooideTaak, context);
        taken.remove(position);
        Toast.makeText(context, R.string.openstaandeTaken_afgevink_toast_text, Toast.LENGTH_SHORT).show();
        Database.getDatabase(context).taakDao().update(afgevinkteTaak);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taken.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // alle textviews en knoppen koppelen aan de layout
        private TextView taakTf;
        private TextView gebruikerTf;
        private TextView kamerTf;
        private ImageView afvinken;
        private ImageView meerInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            taakTf = itemView.findViewById(R.id.openstaandeTakenRow_taak_tf_id);
            gebruikerTf = itemView.findViewById(R.id.openstaandeTakenRow_persoon_tf_id);
            kamerTf = itemView.findViewById(R.id.openstaandeTakenRow_kamer_tf_id);
            afvinken = itemView.findViewById(R.id.openstaandeTakenRow_afvinken_btn_id);
            meerInfo = itemView.findViewById(R.id.openstaandeTakenRow_meerInfo_btn_id);
        }
    }
}
