package com.example.infs3634_assignment_garden;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(Quiz...quiz);

    @Delete
    void delete(Quiz...quiz);

    @Insert
    void insert2(Quiz quiz);

    @Update
    void update(Quiz...quiz);

    @Query("SELECT * FROM Quiz")
    List<Quiz> getQuiz();

    @Query("DELETE FROM Quiz WHERE id =:id")
    void deleteAllQuiz(String id);




}
