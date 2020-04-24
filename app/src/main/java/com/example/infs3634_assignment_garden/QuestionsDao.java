package com.example.infs3634_assignment_garden;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634_assignment_garden.entities.Question;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert
    void insert(Question...question);

    @Query("SELECT * FROM Question WHERE topic = :topic")
    List<Question> populateQuestionBank(String topic);

    @Query("SELECT * FROM Question")
    List<Question> getData();

    @Query("DELETE FROM Question")
    void deleteAllQuestions();



}
