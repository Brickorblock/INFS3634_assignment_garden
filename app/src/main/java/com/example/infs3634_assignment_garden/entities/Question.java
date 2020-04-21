package com.example.infs3634_assignment_garden.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Question {
    private String topic;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    private String answer;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Question(String topic, String question, String choice1, String choice2, String choice3, String choice4, String answer) {
        this.topic = topic;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public static ArrayList<Question> getTempQuestions() {

        ArrayList<Question> Questions = new ArrayList<>();

        Questions.add(new Question("Stars", "We believe there is a black hole at the centre of our galaxy because:", "The region appears dark in optical images", "We detect bursts of EM radiation from the centre.", "All stars near the centre orbit a massive central object.", "Predictive algorithms show it.", "All stars near the centre orbit a massive central object."));
        Questions.add(new Question("Stars", "How long does it take for Alpha Centauri’s light to reach Earth?", "About 50 months", "About 50 days", "About 50 minutes", "About 50 years", "About 50 months"));

        return Questions;
    }

    public static ArrayList<Question> createQuestions(ArrayList<Question> questions) {

        ArrayList<Question> myquestions = getTempQuestions();
        questions.addAll(myquestions);

        return questions;

    }


    @Override
    public String toString() {
        return "Question{" +
                "topic='" + topic + '\'' +
                ", question='" + question + '\'' +
                ", choice1='" + choice1 + '\'' +
                ", choice2='" + choice2 + '\'' +
                ", choice3='" + choice3 + '\'' +
                ", choice4='" + choice4 + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}