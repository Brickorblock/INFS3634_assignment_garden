package com.example.infs3634_assignment_garden;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;
import com.example.infs3634_assignment_garden.entities.Topics;

@Database(entities = {Question.class, Quiz.class, Plant.class, Topics.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase instance;

    public abstract QuestionsDao questionsDao();
//
//    public abstract GardenDao gardenDao();
//
    public abstract QuizDao quizDao();

    public abstract PlantDao plantDao();

    public abstract TopicDao topicDao();



}
