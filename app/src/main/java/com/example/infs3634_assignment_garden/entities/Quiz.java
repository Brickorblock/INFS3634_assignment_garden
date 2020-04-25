package com.example.infs3634_assignment_garden.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import static com.example.infs3634_assignment_garden.MainActivity.garden;

//This class stores quiz objects which are used to allow users to access quizzes for specific plants.
//Done through using a plant Index- essentially a foreign that links plants to quizzes.
@Entity
public class Quiz {

    public int plantIndex;
    private int questions;
    private String topic;
    public static final int QUESTION_SIZE = 10;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Quiz() {
    }

    public Quiz(Plant plant, int questions) {
        this.plantIndex = garden.plantIndexSearch(plant);
        this.questions = questions;
        this.topic = plant.getTopic();
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPlantIndex() {
        return plantIndex;
    }

    public void setPlantIndex(int plantIndex) {
        this.plantIndex = plantIndex;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static ArrayList<Quiz> createQuizzes(ArrayList<Quiz> quizList) {

        ArrayList<Quiz> myquizzes = garden.getTempQuizzes();
        quizList.addAll(myquizzes);

        return quizList;

    }

    @Override
    public String toString() {
        return "Quiz{" +
                "plantIndex=" + plantIndex +
                ", questions=" + questions +
                ", topic='" + topic + '\'' +
                ", id=" + id +
                '}';
    }
}
