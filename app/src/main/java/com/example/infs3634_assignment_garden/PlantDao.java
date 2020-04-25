package com.example.infs3634_assignment_garden;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634_assignment_garden.entities.Plant;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert
    void insert(Plant...plant);

    @Update
    void update(Plant...plant);

    @Query("SELECT * FROM Plant")
    List<Plant> getPlant();

    @Query("DELETE FROM Plant")
    void deleteAllPlant();

    @Query("SELECT PlantIndex FROM Plant")
    List<Plant> getPlantIndex();




}
