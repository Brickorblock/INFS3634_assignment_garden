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

    @Update
    void update(Garden garden);

    @Delete
    void delete(Garden garden);

    @Query("SELECT * FROM Garden")
    List<Garden> getGarden();





}
