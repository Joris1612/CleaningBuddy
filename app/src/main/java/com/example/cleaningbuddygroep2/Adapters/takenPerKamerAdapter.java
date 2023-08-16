package com.example.cleaningbuddygroep2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.R;
import com.example.cleaningbuddygroep2.Database.Database;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;

import java.util.List;

public class takenPerKamerAdapter extends RecyclerView.Adapter<takenPerKamerAdapter.ViewHolder> {
    private List<Taak> taken;
    private List<Gebruiker> gebruikers;
    private Kamer kamer;
    private Context context;

    public takenPerKamerAdapter(List<Taak> taken, List<Gebruiker> gebruikers, Kamer kamer, Context context) {
        // attributen vullen vanuit activity
        this.taken = taken;
        this.gebruikers = gebruikers;
        this.kamer = kamer;
        this.context = context;
    }

    // Viewholder aanmaken
    @Override
    public takenPerKamerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taken_per_kamer_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(takenPerKamerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // alle attributen koppelen op basis van de positie in de lijst
        Taak taak = taken.get(position);
        Gebruiker gebruiker = gebruikers.get(position);
        holder.taakTf.setText(taak.getNaam());
        holder.gebruikerTf.setText(gebruiker.getGebruikersNaam());
        holder.kamerTf.setText(kamer.getNaam());

        // taak verwijderen
        holder.verwijderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verwijderen(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taken.size();
    }

    // functie om taak te verwijderen, zowel van de lijst als uit de database
    private void verwijderen(int position) {
        Taak verwijderdeTaak = taken.remove(position);
        Toast.makeText(context, R.string.takenPerKamer_verwijderen_toast_text, Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
        Database.getDatabase(context).taakDao().delete(verwijderdeTaak);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // alle textviews en knoppen kopppelen aan de layout
        private TextView taakTf;
        private TextView gebruikerTf;
        private TextView kamerTf;
        private ImageView verwijderBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            taakTf = itemView.findViewById(R.id.takenPerKamerRow_taak_tf_id);
            gebruikerTf = itemView.findViewById(R.id.takenPerKamerRow_gebruiker_tf_id);
            kamerTf = itemView.findViewById(R.id.takenPerKamerRow_kamer_tf_id);
            verwijderBtn = itemView.findViewById(R.id.takenPerKamerRow_verwijderen_btn_id);
        }
    }
}
