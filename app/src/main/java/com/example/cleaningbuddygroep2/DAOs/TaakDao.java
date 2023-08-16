package com.example.cleaningbuddygroep2.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.cleaningbuddygroep2.Converter.Converter;
import com.example.cleaningbuddygroep2.Models.Taak;

import java.util.List;

@Dao
@TypeConverters({Converter.class})
public interface TaakDao {

    @Query("SELECT COUNT(id) FROM taken WHERE kamerId =:kamerId")
    Integer aantalTakenPerKamer(Integer kamerId);

    @Query("SELECT * FROM taken")
    List<Taak> alleTaken();

    @Query("SELECT naam FROM taken WHERE id = :id")
    String naamZoekenPerId(Integer id);

    @Query("SELECT * FROM taken WHERE kamerId = :kamerId")
    List<Taak> taakPerKamer(Integer kamerId);

    @Query("SELECT gebruikerId FROM taken WHERE id = :id")
    Integer selecteerGebruiker(Integer id);

    @Query("SELECT id FROM taken WHERE naam = :naam")
    Integer selecteerTaakId(String naam);

    @Query("SELECT * from taken WHERE id = :id")
    Taak getTaaknaamViaId(Integer id);

    @Query("SELECT naam FROM taken")
    List<String> alleTakenNamen();

    @Insert
    void insert(Taak taak);

    @Update
    void update(Taak taak);

    @Delete
    void delete(Taak taak);
}