package com.example.cleaningbuddygroep2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Activities.TakenPerKamerActivity;
import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Kamer;

import java.util.List;

public class alleKamersAdapter extends RecyclerView.Adapter<alleKamersAdapter.ViewHolder> {

    private List<Kamer> kamers;
    private List<Integer> takenAantallen;
    private Context context;

    public alleKamersAdapter(List<Kamer> kamers, List<Integer> takenAantallen, Context context) {
        // attributen vullen vanuit de activity
        this.kamers = kamers;
        this.takenAantallen = takenAantallen;
        this.context = context;
    }

    // viewholder aanmaken
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alle_kamers_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // informatie ophalen uit de lijsten op basis van de positie in de lijst
        Kamer kamer = kamers.get(position);
        Integer aantal = takenAantallen.get(position);
        String idString = Integer.toString(kamer.getId());
        String aantalString = Integer.toString(aantal);
        holder.idTf.setText(idString);
        holder.aantalTf.setText(aantalString);
        holder.naamTf.setText(kamer.getNaam());
        // kamer verwijderen
        holder.verwijderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verwijderen(position);
            }
        });
        // knop om naar de activity alleTaken te gaan
        holder.alleTakenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gaNaarAlleTaken(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kamers.size();
    }

    // functie om kamer te verwijderen, zowel uit de lijst als uit de database
    private void verwijderen(int position) {
        Kamer verwijderdeKamer = kamers.remove(position);
        Toast.makeText(context, R.string.alleKamers_verwijderen_toast_text, Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
        Database.getDatabase(context).kamerDao().delete(verwijderdeKamer);
    }

    // intent om naar alle taken te gaan
    private void gaNaarAlleTaken(View view, int position) {
        Intent intent = new Intent(context, TakenPerKamerActivity.class);
        Integer kamerId = kamers.get(position).getId();
        intent.putExtra("kamerId", kamerId);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // alle textviews en knoppen koppelen aan de layout
        private TextView idTf;
        private TextView naamTf;
        private TextView aantalTf;

        private ImageView verwijderBtn;
        private ImageView alleTakenBtn;
        private ImageView aanpassenBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            idTf = itemView.findViewById(R.id.alleKamersRow_id_tf_id);
            naamTf = itemView.findViewById(R.id.alleKamersRow_taak_tf_id);
            aantalTf = itemView.findViewById(R.id.alleKamersRow_aantalTaken_tf_id);
            verwijderBtn = itemView.findViewById(R.id.alleKamers_verwijderen_btn_id);
            alleTakenBtn = itemView.findViewById(R.id.alleKamersRow_alleTaken_btn_id);
            aanpassenBtn = itemView.findViewById(R.id.alleKamersRow_aanpassen_btn_id);

        }
    }
}
