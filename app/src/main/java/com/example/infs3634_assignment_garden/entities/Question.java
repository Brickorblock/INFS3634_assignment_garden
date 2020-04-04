package com.example.infs3634_assignment_garden.entities;

public class Question {
    private String topic;
    private String question;
    private String[] choices = new String[4];
    private char answer;

    public Question(String topic, String question, String[] choices, char answer) {
        this.topic = topic;
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

}
