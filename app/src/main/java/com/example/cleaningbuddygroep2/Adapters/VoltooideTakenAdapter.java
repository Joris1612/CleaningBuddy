package com.example.cleaningbuddygroep2.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddygroep2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VoltooideTakenAdapter extends RecyclerView.Adapter<VoltooideTakenAdapter.ViewHolder> {
    private List<String> namen;
    private List<Date> data;

    // adapter constructor
    public VoltooideTakenAdapter(List<String> namen, List<Date> data) {
        this.namen = namen;
        this.data = data;
    }

    // De link tussen de adapter en de row layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // textviews ophalen om te kunnen vullen met informatie
        public TextView textViewGebruiker;
        public TextView textViewDatum;

        // viewholder constructor
        public ViewHolder(View itemView) {
            super(itemView);
            textViewGebruiker = itemView.findViewById(R.id.voltooideTaken_row_gebruiker_tf);
            textViewDatum = itemView.findViewById(R.id.voltooideTaken_row_datum_tf);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voltooide_taken_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String naam = namen.get(position);
        Date datum = data.get(position);
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String datumString = simpleDateFormat.format(datum);
        holder.textViewGebruiker.setText(naam);
        holder.textViewDatum.setText(datumString);
    }

    @Override
    public int getItemCount() {
        return namen.size();
    }
}