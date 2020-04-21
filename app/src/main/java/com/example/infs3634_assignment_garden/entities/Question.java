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
        Questions.add(new Question("Stars", "Quasars are:", "A type of neutron stars", "A dying star", "A type of star", "Powered by supermassive blackholes", "Powered by supermassive blackholes"));
        Questions.add(new Question("Stars", "What is the lifecycle of an average star:", "Nebula, Main Sequence Star, Supernova, White Dwarf", "Nebula, Main Sequence Star, Planetary Nebula, White Dwarf", "Main Sequence Star, Nebula, Planetary Nebula, White Dwarf", "Nebula, Main Sequence Star, White Dwarf, Planetary Nebula", "Nebula, Main Sequence Star, Planetary Nebula, White Dwarf"));
        Questions.add(new Question("Stars", "What is the main reason a Star collapses into a black hole?", "Gravitational forces finally exceed the star’s nuclear forces", "It passes the age of 3 billion years", "The star’s core becomes full of helium", "It has lost too much mass", "Gravitational forces finally exceed the star’s nuclear forces"));
        Questions.add(new Question("Stars", "What are stars mostly comprised of?", "Helium", "Hydrogen", "Carbon", "Iron", "Hydrogen"));
        Questions.add(new Question("Stars", "Which is the closest star to us?", "Proxima Centauri", "Sirius", "Alpha Centauri", "Ross 128", "Alpha Centauri"));
        Questions.add(new Question("Stars", "Stars are glowing spheres of hot…", "Dust", "Metal", "Liquid", "Gas", "Gas"));
        Questions.add(new Question("Stars", "What is a light-year?", "How far light travels in a year", "How long it takes light to reach earth", "How long it takes light to reach the Sun", "A measurement of time", "How far light travels in a year"));
        Questions.add(new Question("Stars", "What process fuels a star?", "Nuclear fission", "Nuclear fusion", "Dark energy", "Neutrino creation", "Nuclear fusion"));
        Questions.add(new Question("Stars", "What type is out star?", "Red giant", "White Dwarf", "Yellow Main Sequence", "Blue giant", "Yellow Main Sequence"));
        Questions.add(new Question("Stars", "What colour are the hottest stars?", "Red", "Yellow", "White", "Blue", "Blue"));
        Questions.add(new Question("Stars", "Apparent magnitude is what?", "How bright a star is relative to us", "How bright a star really is", "The size of a star relative to us", "How large a star really is", "How bright a star is relative to us"));
        Questions.add(new Question("Stars", "What protects the Earth from Solar Wind?", "Greenhouse effect", "Earth’s magnetosphere", "The ozone layer", "Asteroid belt", "Earth’s magnetosphere"));
        Questions.add(new Question("Stars", "What is the product of two neutron stars colliding?", "Neutrinos", "Solar Wind", "Gravitational waves", "Nuclear fusion", "Gravitational waves"));
        Questions.add(new Question("Stars", "Where is the energy in the Sun generated?", "Just under the surface", "In the mantle", "In the radiative zone", "In its core", "In its core"));
        Questions.add(new Question("Stars", "What are the layers of the Sun (in asc order)?", "Core, Radiative, Convection, Photosphere, Chromosphere, Corona", "Core, Radiative, Corona, Convection", "Core, Convection, Corona, Photosphere, Chromosphere, Radiative", "Core, Corona, Chromosphere", "Core, Radiative, Convection, Photosphere, Chromosphere, Corona"));
        Questions.add(new Question("Stars", "What colour is the coolest star?", "Yellow", "White", "Blue", "Red", "White"));
        Questions.add(new Question("Stars", "Which property of a star tells us the composition of that Star?", "Luminosity", "Apparent magnitude", "Spectrum", "Absolute magnitude", "Spectrum"));
        Questions.add(new Question("Stars", "A star that explodes is known as a", "Nebula", "Supershell", "Neutron star", "Supernova", "Supernova"));

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