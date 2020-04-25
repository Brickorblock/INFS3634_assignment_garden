package com.example.infs3634_assignment_garden;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634_assignment_garden.entities.Garden;


import java.util.List;

@Dao
public interface GardenDao {

    @Insert
    void insert(Garden garden);

    @Query("UPDATE Garden SET coins =:coins")
    void updateCoin(int coins, int id);

    @Query("DELETE FROM Garden")
    void delete();

    @Query("SELECT ambienceLvl FROM Garden")
    List<Garden> getGarden();





}
