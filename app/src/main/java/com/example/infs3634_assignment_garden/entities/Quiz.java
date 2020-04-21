package com.example.infs3634_assignment_garden.entities;

import java.util.ArrayList;

public class Quiz {
    public int plantIndex;
    private int questions;
    private String topic;
    public static final int QUESTION_SIZE = 10;

    public Quiz(Plant plant, int questions) {
        this.plantIndex = Garden.plantIndexSearch(plant);
        this.questions = questions;
        this.topic = plant.getTopic();
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

        ArrayList<Quiz> myquizzes = Garden.getTempQuizzes();
        quizList.addAll(myquizzes);

        return quizList;

    }

    @Override
    public String toString() {
        return "Quiz{" +
                "plantIndex=" + plantIndex +
                ", questions=" + questions +
                ", topic='" + topic + '\'' +
                '}';
    }
}
