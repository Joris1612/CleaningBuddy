package com.example.cleaningbuddygroep2.Models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cleaningbuddygroep2.Converter.Converter;
import com.example.cleaningbuddygroep2.Database.Database;

import java.util.Date;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(entity = Gebruiker.class,
                parentColumns = "id",
                childColumns = "gebruikerId",
                onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = Taak.class,
                parentColumns = "id",
                childColumns = "taakId",
                onDelete = ForeignKey.CASCADE)
}
)

public class VoltooideTaken {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String naam;
    @TypeConverters({Converter.class})
    private Date datum;
    private Integer gebruikerId;
    private Integer taakId;

    public VoltooideTaken(String naam, Date datum, Integer gebruikerId, Integer taakId) {
        this.naam = naam;
        this.datum = datum;
        this.gebruikerId = gebruikerId;
        this.taakId = taakId;
    }

    public static List<VoltooideTaken> alleVoltooideTaken(Context context) {
        return Database.getDatabase(context).VoltooideTakenDao().alleVoltooideTaken();
    }

    public static List<VoltooideTaken> voltooideTakenPerTaakOplopend(Integer taakId, Context context) {
        return Database.getDatabase(context).VoltooideTakenDao().voltooideTakenPerTaakOplopend(taakId);
    }

    public static List<VoltooideTaken> voltooideTakenPerTaakAflopend(Integer taakId, Context context) {
        return Database.getDatabase(context).VoltooideTakenDao().voltooideTakenPerTaakAflopend(taakId);
    }

    public static void voltooideTaakToevoegen(VoltooideTaken voltooideTaken, Context context) {
        if (voltooideTaken != null) {
            Database.getDatabase(context).VoltooideTakenDao().insert(voltooideTaken);
        }
    }

    public static VoltooideTaken meestRecenteVoltooideTaak(Integer taakId, Context context) {
        return Database.getDatabase(context).VoltooideTakenDao().meestRecenteVoltooideTaak(taakId);
    }

    public static boolean taakIsInHistory(Integer taakId, Context context) {
        return Database.getDatabase(context).VoltooideTakenDao().alleTaakIDs().contains(taakId);
    }

    public String getNaam() {
        return naam;
    }

    public Date getDatum() {
        return datum;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGebruikerId() {
        return gebruikerId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGebruikerId(Integer gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setTaakId(Integer taakId) {
        this.taakId = taakId;
    }

    public Integer getTaakId() {
        return taakId;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}