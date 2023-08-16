package com.example.cleaningbuddygroep2.Models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cleaningbuddygroep2.Database.Database;

import java.util.List;

@Entity(tableName = "Gebruikers")
public class Gebruiker {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String gebruikersNaam;
    private String wachtwoord;

    public Gebruiker(String gebruikersNaam, String wachtwoord) {
        this.gebruikersNaam = gebruikersNaam;
        this.wachtwoord = wachtwoord;
    }

    public static Gebruiker zoekenPerId(Integer id, Context context) {
        return Database.getDatabase(context).gebruikerDao().zoekenPerId(id);
    }

    public static void gebruikerToevoegen(Gebruiker gebruiker, Context context) {
        if (gebruiker != null) {
            Database.getDatabase(context).gebruikerDao().insert(gebruiker);
        }
    }

    public static List<Gebruiker> alleGebruikers(Context context) {
        return Database.getDatabase(context).gebruikerDao().alleGebruikers();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGebruikersNaam(String gebruikersNaam) {
        this.gebruikersNaam = gebruikersNaam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Integer getId() {
        return id;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    @Override
    public String toString() {
        return getGebruikersNaam();
    }
}