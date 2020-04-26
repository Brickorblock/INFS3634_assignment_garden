package com.example.infs3634_assignment_garden.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;

@Database(entities = {Question.class, Quiz.class, Plant.class, Garden.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase instance;

    public abstract QuestionsDao questionsDao();

    public abstract GardenDao gardenDao();

    public abstract QuizDao quizDao();

    public abstract PlantDao plantDao();

}
