package com.example.infs3634_assignment_garden;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3634_assignment_garden.entities.Plant;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert
    void insert(Plant plant);

    @Query("SELECT * FROM Plant")
    LiveData<List<Plant>> getPlant();

}
