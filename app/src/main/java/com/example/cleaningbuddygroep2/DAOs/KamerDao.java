package com.example.cleaningbuddygroep2.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddygroep2.Models.Kamer;

import java.util.List;

@Dao
public interface KamerDao {

    @Query("SELECT * FROM kamers")
    List<Kamer> alleKamers();

    @Query("SELECT * FROM kamers WHERE id = :id")
    Kamer zoekenPerId(int id);

    @Query("SELECT id FROM kamers WHERE naam = :naam")
    Integer idOphalen(String naam);

    @Query("SELECT naam FROM kamers")
    List<String> alleKamerNamen();

    @Insert
    void insert(Kamer kamer);

    @Update
    void update(Kamer kamer);

    @Delete
    void delete(Kamer kamer);
}