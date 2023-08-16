package com.example.cleaningbuddygroep2.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddygroep2.Models.VoltooideTaken;

import java.util.List;

@Dao
public interface VoltooideTakenDao {
    @Query("SELECT * FROM voltooidetaken")
    List<VoltooideTaken> alleVoltooideTaken();

    @Query("SELECT * FROM voltooidetaken WHERE taakId = :taakId ORDER BY datum ASC")
    List<VoltooideTaken> voltooideTakenPerTaakOplopend(Integer taakId);

    @Query("SELECT * FROM voltooidetaken WHERE taakId = :taakId ORDER BY datum DESC")
    List<VoltooideTaken> voltooideTakenPerTaakAflopend(Integer taakId);

    @Query("SELECT * FROM voltooidetaken WHERE taakId = :taakId ORDER BY datum DESC LIMIT 1 ")
    VoltooideTaken meestRecenteVoltooideTaak(Integer taakId);

    @Query("SELECT taakId FROM voltooidetaken")
    List<Integer> alleTaakIDs();

    @Insert
    void insert(VoltooideTaken voltooideTaken);

    @Update
    void update(VoltooideTaken voltooideTaken);

    @Delete
    void delete(VoltooideTaken voltooideTaken);
}