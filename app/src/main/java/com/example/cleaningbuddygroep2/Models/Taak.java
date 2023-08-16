package com.example.cleaningbuddygroep2.Models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cleaningbuddygroep2.Converter.Converter;
import com.example.cleaningbuddygroep2.Database.Database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "Taken",
        foreignKeys = {
                @ForeignKey(entity = Gebruiker.class,
                        parentColumns = "id",
                        childColumns = "gebruikerId",
                        onDelete = ForeignKey.SET_NULL),
                @ForeignKey(entity = Kamer.class,
                        parentColumns = "id",
                        childColumns = "kamerId",
                        onDelete = ForeignKey.SET_NULL)
        }
)

public class Taak implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String naam;
    private Integer interval;
    private String omschrijving;
    private Integer gebruikerId;
    private Integer kamerId;
    @TypeConverters({Converter.class})
    private Date aanmaakDatum;

    public Taak(String naam, Integer interval, Integer gebruikerId, Integer kamerId, String omschrijving, Date aanmaakDatum) {
        this.naam = naam;
        this.interval = interval;
        this.gebruikerId = gebruikerId;
        this.kamerId = kamerId;
        this.omschrijving = omschrijving;
        this.aanmaakDatum = aanmaakDatum;
    }

    public Taak() {
    }

    public static void taakToevoegen(Taak taak, Context context) {
        if (taak != null) {
            Database.getDatabase(context).taakDao().insert(taak);
        }
    }

    public static Integer aantalTakenPerKamer(Integer kamerId, Context context) {
        return Database.getDatabase(context).taakDao().aantalTakenPerKamer(kamerId);
    }

    public static List<Taak> alleTaken(Context context) {
        return Database.getDatabase(context).taakDao().alleTaken();
    }

    public static List<String> alleTakenNamen(Context context) {
        return Database.getDatabase(context).taakDao().alleTakenNamen();
    }

    public static Integer selecteerTaakId(String naam, Context context) {
        return Database.getDatabase(context).taakDao().selecteerTaakId(naam);
    }

    public static String naamZoekenPerId(Integer taakId, Context context) {
        return Database.getDatabase(context).taakDao().naamZoekenPerId(taakId);
    }

    public static Integer selecteerGebruikerId(Integer taakId, Context context) {
        return Database.getDatabase(context).taakDao().selecteerGebruiker(taakId);
    }

    public static List<Taak> taakPerKamer(Integer kamerId, Context context) {
        return Database.getDatabase(context).taakDao().taakPerKamer(kamerId);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGebruikerId(Integer gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public Integer getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Integer getInterval() {
        return interval;
    }

    public Integer getGebruikerId() {
        return gebruikerId;
    }

    public Integer getKamerId() {
        return kamerId;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Date getAanmaakDatum() {
        return aanmaakDatum;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public void setKamerId(Integer kamerId) {
        this.kamerId = kamerId;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setAanmaakDatum(Date aanmaakDatum) {
        this.aanmaakDatum = aanmaakDatum;
    }
}