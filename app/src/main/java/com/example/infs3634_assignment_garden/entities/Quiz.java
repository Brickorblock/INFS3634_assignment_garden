package com.example.infs3634_assignment_garden.entities;

import java.util.ArrayList;

public class Quiz {
    private Plant plant;
    private ArrayList<Question> questions;
    private String topic;

    public Quiz(Plant plant, ArrayList<Question> questions, String topic) {
        this.plant = plant;
        this.questions = questions;

        for (int i = 0; i < 10; i++) {

        }
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getSize(){
        return questions.size();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
