package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private Plant plant;
    private String questions;
    private String topic;

    public Quiz(Plant plant, String questions, String topic) {
        this.plant = plant;
        this.questions = questions;
        this.topic = topic;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static ArrayList<Quiz> getTempQuizzes() {

        ArrayList<Quiz> Quizzes = new ArrayList<>();

        Quizzes.add(new Quiz(new Plant(R.drawable.tree_sample, "Evergreen", "Solar Systems"), "10", "Solar Systems"));
        Quizzes.add(new Quiz(new Plant(R.drawable.tree_sample, "Evergreen", "Cosmology"), "10", "Cosmology"));
        Quizzes.add(new Quiz(new Plant(R.drawable.tree_sample, "Evergreen", "Stars"), "10", "Stars"));

        return Quizzes;
    }

    public static List<Quiz> CreateQuizzes (List<Quiz> quiz) {

        ArrayList<Quiz> myquizzes = getTempQuizzes();
        quiz.addAll(myquizzes);

        return quiz;

    }

    public static String getplantname(Quiz quiz){

        Plant Plant = quiz.getPlant();

        String plantname = Plant.getName();

        return plantname;
    }

}
