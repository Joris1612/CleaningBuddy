package com.example.cleaningbuddygroep2.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddygroep2.Models.Gebruiker;


import java.util.List;

@Dao
public interface GebruikerDao {

    @Query("SELECT * FROM gebruikers")
    List<Gebruiker> alleGebruikers();

    @Query("SELECT * FROM gebruikers WHERE id = :id")
    Gebruiker zoekenPerId(int id);

    @Query("SELECT id FROM gebruikers WHERE gebruikersNaam = :naam")
    Integer idOphalen(String naam);

    @Insert
    void insert(Gebruiker gebruiker);

    @Update
    void update(Gebruiker gebruiker);

    @Delete
    void delete(Gebruiker gebruiker);
}