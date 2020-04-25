package com.example.infs3634_assignment_garden;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3634_assignment_garden.entities.Topics;

import java.util.List;

@Dao
public interface TopicDao {
    @Insert
    void insert(Topics...topics);

    @Query("SELECT * FROM Topics")
    List<Topics> getTopic();

    @Query("DELETE FROM Topics")
    void deleteAllTopic();
}
