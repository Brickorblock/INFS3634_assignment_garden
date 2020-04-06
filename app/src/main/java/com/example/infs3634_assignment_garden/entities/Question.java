package com.example.infs3634_assignment_garden.entities;

import java.util.ArrayList;

public class Question {
    private String topic;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    private String answer;

    public Question(String topic, String question, String choice1, String choice2, String choice3, String choice4, String answer) {
        this.topic = topic;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
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

        Questions.add(new Question("Solar Systems", "Which of the following is an example of a celestial body?", "Sun", "Moon", "Stars", "All of the Above", "All of the Above"));
        Questions.add(new Question("Solar Systems", "Which planet has rings around it?", "Jupiter", "Saturn", "Uranus", "All of the Above", "All of the Above"));
        Questions.add(new Question("Solar Systems", "The Sun is ___ million km away from the Earth.", "100", "150", "200", "250", "150"));
        Questions.add(new Question("Solar Systems", "The diameter of Moon is ______ that of the earth.", "1/2", "1/3", "1/4", "2/3", "1/4"));
        Questions.add(new Question("Solar Systems", "The moon moves around the earth in about _____ days.", "11", "33", "60", "27", "27"));
        Questions.add(new Question("Solar Systems", "The moon takes _____ days to complete one spin.", "27", "35", "16", "9", "27"));
        Questions.add(new Question("Solar Systems", "The majority of asteroids are found between the orbits of:", "Mars and Jupiter", "Earth and Mars", "Jupiter and Saturn", "Saturn and Uranus", "Mars and Jupiter"));
        Questions.add(new Question("Solar Systems", "Which planet takes longest time for one spin on its axis?", "Venus", "Mercury", "Saturn", "Uranus", "Mercury"));
        Questions.add(new Question("Solar Systems", "Which planet takes longest time to orbit around the Sun?", "Neptune", "Mercury", "Saturn", "Uranus", "Neptune"));
        Questions.add(new Question("Solar Systems", "Which is the most abundant in Venus’ atmosphere?", "Nitrogen", "Carbon Dioxide", "Oxygen", "Ozone", "Carbon Dioxide"));
        Questions.add(new Question("Solar Systems", "Which of the following is true of our solar system?", "Not all planets orbit the sun in the same direction", "Not all planets spin in the same direction as they orbit", "It has an equal number of stars and planet", "It is less than 1 million years old", "Not all planets spin in the same direction as they orbit"));
        Questions.add(new Question("Solar Systems", "Venus is most similar in mass and density to which solar system object?", "Jupiter", "Earth", "Mars", "Phobos", "Earth"));
        Questions.add(new Question("Solar Systems", "Which of the following is NOT a dwarf planet?", "Pluto", "Charon", "Eris", "Ceres", "Charon"));
        Questions.add(new Question("Solar Systems", "When did the solar system form?", "3.2 billion years ago", "10.8 billion years ago", "7.5 billion years ago", "4.6 billion years ago", "4.6 billion years ago"));
        Questions.add(new Question("Solar Systems", "Which of the following is an asteroid?", "Eris", "Charon", "Vesta", "Pluto", "Vesta"));
        Questions.add(new Question("Solar Systems", "A greenhouse gas traps ____ in the atmosphere", "Ozone", "Gamma rays", "Heat", "Ultraviolet radiation", "Heat"));
        Questions.add(new Question("Solar Systems", "Which is the reason why Venus is hotter than Mercury?", "It’s closer to the sun", "It’s size", "The greenhouse effect", "Climate change", "The greenhouse effect"));
        Questions.add(new Question("Solar Systems", "Which moon on Saturn is considered to have the best chance at hosting life?", "Enceladus", "Mimas", "Rhea", "Phoebe", "Enceladus"));
        Questions.add(new Question("Solar Systems", "What is the main component of Venus’ clouds?", "Water vapour", "Sulphuric acid", "Methane", "Carbon dioxide", "Sulphuric acid"));
        Questions.add(new Question("Solar Systems", "Name the molecule or element that is part of the current atmosphere but would not be in the original atmosphere of the Earth:", "Carbon Dioxide", "Oxygen", "Methane", "Nitrogen", "Oxygen"));





        Questions.add(new Question("Cosmology", "The expansion age of the universe is about _________", "10.2 billion years", "13.7 billion years", "5.3 billion years", "16.9 billion years", "13.7 billion years"));
        Questions.add(new Question("Cosmology", "The rate of expansion of the universe is __________", "Decelerating", "Accelerating", "Constant", "Zero", "Accelerating"));
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