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

    @Query("UPDATE Plant SET growthTotal = :growthTotal WHERE plantIndex =:id")
    void updateExp(int growthTotal, int id);

    @Query("UPDATE Plant SET quizReady = :quizReady WHERE plantIndex =:id")
    void updateQuizReady(Boolean quizReady, int id);

    @Query("SELECT * FROM Plant")
    List<Plant> getPlant();

    @Query("DELETE FROM Plant")
    void deleteAllPlant();

    @Query("SELECT plantIndex FROM Plant WHERE plantIndex =:id")
    int getPlantIndex(int id);

}
