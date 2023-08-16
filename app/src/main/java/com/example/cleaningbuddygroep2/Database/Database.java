package com.example.cleaningbuddygroep2.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cleaningbuddygroep2.Converter.Converter;
import com.example.cleaningbuddygroep2.DAOs.GebruikerDao;
import com.example.cleaningbuddygroep2.DAOs.KamerDao;
import com.example.cleaningbuddygroep2.DAOs.TaakDao;
import com.example.cleaningbuddygroep2.DAOs.VoltooideTakenDao;
import com.example.cleaningbuddygroep2.Models.Gebruiker;
import com.example.cleaningbuddygroep2.Models.Kamer;
import com.example.cleaningbuddygroep2.Models.Taak;
import com.example.cleaningbuddygroep2.Models.VoltooideTaken;

@androidx.room.Database(entities = {Kamer.class, Taak.class, Gebruiker.class, VoltooideTaken.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class Database extends RoomDatabase {
    public abstract GebruikerDao gebruikerDao();

    public abstract KamerDao kamerDao();

    public abstract TaakDao taakDao();

    public abstract VoltooideTakenDao VoltooideTakenDao();

    public static Database getDatabase(Context context) {
        Database database;
        synchronized (Database.class) {
            database = Room.databaseBuilder(context, Database.class, "CleaningBuddyDB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}