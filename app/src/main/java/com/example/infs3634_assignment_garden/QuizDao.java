package com.example.infs3634_assignment_garden;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(Quiz...quiz);

    @Update
    void update(Quiz...quiz);

    @Query("SELECT * FROM Quiz")
    List<Quiz> getQuiz();

    @Query("SELECT topic FROM Quiz")
    List<Quiz> getTopic();

    @Query("DELETE FROM Quiz")
    void deleteAllQuiz();

}
