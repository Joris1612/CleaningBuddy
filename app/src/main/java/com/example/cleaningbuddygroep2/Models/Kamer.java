package com.example.cleaningbuddygroep2.Models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cleaningbuddygroep2.Database.Database;

import java.util.List;

@Entity(tableName = "Kamers")
public class Kamer {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String naam;

    public static void kamerToevoegen(Kamer kamer, Context context) {
        if (kamer != null) {
            Database.getDatabase(context).kamerDao().insert(kamer);
        }
    }

    public static Integer idOphalenOBVNaam(String naam, Context context) {
        return Database.getDatabase(context).kamerDao().idOphalen(naam);
    }

    public static Kamer zoekenPerId(Integer kamerId, Context context) {
        return Database.getDatabase(context).kamerDao().zoekenPerId(kamerId);
    }

    public static List<String> alleKamerNamen(Context context) {
        return Database.getDatabase(context).kamerDao().alleKamerNamen();
    }

    public static List<Kamer> alleKamers(Context context) {
        return Database.getDatabase(context).kamerDao().alleKamers();
    }

    public Kamer(String naam) {
        this.naam = naam;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return getNaam();
    }
}