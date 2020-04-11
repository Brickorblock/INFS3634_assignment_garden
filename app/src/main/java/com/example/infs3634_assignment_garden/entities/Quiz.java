package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public Plant plant;
    private int questions;
    private String topic;
    public static final int QUESTION_SIZE = 10;

    public Quiz(Plant plant, int questions) {
        this.plant = plant;
        this.questions = questions;
        this.topic = plant.getSubject();
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
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

        ArrayList<Quiz> myquizzes = Garden.getTempQuizzes();
        quizList.addAll(myquizzes);

        return quizList;

    }

    public static String getplantname(Quiz quiz){

        Plant Plant = quiz.getPlant();

        String plantname = Plant.getName();

        return plantname;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "plant=" + plant.getName() +
                ", questions='" + questions + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
