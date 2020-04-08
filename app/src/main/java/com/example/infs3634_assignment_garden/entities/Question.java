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
        Questions.add(new Question("Cosmology", "What type of galaxy shape is the milky way?", "Elliptical", "Irregular", "Spiral", "Barred spiral", "Spiral"));
        Questions.add(new Question("Cosmology", "What can be said about the location of the centre of our expanding universe?", "The Earth is at the center", "The Sun is at the center", "the Milky Way Galaxy is at the center", "The universe does not have a center", "The universe does not have a center"));
        Questions.add(new Question("Cosmology", "How are galaxies important to our existence?", "Without galaxies, the universe could not be expanding", "Without galaxies, there could not have been a Big Bang", "Galaxies recycle elements produced in stars into future star generations", "Galaxies provide the gravity that prevents us from falling off the earth", "Galaxies recycle elements produced in stars into future star generations"));
        Questions.add(new Question("Cosmology", "Galaxies that move closer to us show a what shift?", "Red Shift", "Green Shift", "Orange Shift", "Blue Shift", "Red Shift"));
        Questions.add(new Question("Cosmology", "In relation to us, most galaxies are…", "Moving away from us", "Moving toward us", "Not moving", "Moving perpendicular to us", "Moving away from us"));
        Questions.add(new Question("Cosmology", "Why is the CMB so cool now?", "The expansion of the universe has cooled the radiation", "Dense clouds of dust have blocked most of it", "The cosmological constant has cooled it", "Hydrogen atoms have condensed on it and chilled it", "The expansion of the universe has cooled the radiation"));
        Questions.add(new Question("Cosmology", "Which piece of evidence supports the Big Bang theory?", "The more distant galaxies are moving the slowest", "The more distant galaxies are moving the quickest", "The more distant galaxies are moving towards us", "Galaxies and space is not changing", "The more distant galaxies are moving the quickest"));
        Questions.add(new Question("Cosmology", "The \"afterglow\" of the energy from the origin of the universe that we see today", "Sunlight", "Cosmic explosions", "Cosmic background radiation", "Doppler effect", "Cosmic background radiation"));
        Questions.add(new Question("Cosmology", "Who discovered that the galaxies were moving away from one another?", "Issac Newton", "Albert Einstein", "Galileo Galile", "Edwin Hubble", "Edwin Hubble"));
        Questions.add(new Question("Cosmology", "What happens if you reverse the galaxy motion and go back in time?", "Everything must have been condensed into a singularity", "Everything would still be where it is today", "Some things would remain, some would have moved back", "Nobody has a clue", "Everything must have been condensed into a singularity"));
        Questions.add(new Question("Cosmology", "What happened to the radiation emitted from the Big Bang?", "It is visible in telescopes", "It has stretched out to the microwave part of the spectrum", "It has moved to the gamma ray part of the spectrum", "It is too old and no longer exists", "It has stretched out to the microwave part of the spectrum"));
        Questions.add(new Question("Cosmology", "What is the universe made up of?", "Energy", "Matter/dark matter", "Spacetime", "All of the above", "All of the above"));
        Questions.add(new Question("Cosmology", "Hubble's law states which of the following?", "We live in an expanding universe", "Galaxies far away look redder through a telescope", "The further away a galaxy is, the faster it is receding", "All of these", "All of these"));
        Questions.add(new Question("Cosmology",  "The first particles of matter included which of the following?", "Electrons", "Helium atoms", "Carbon atoms", "All of these", "Electron"));
        Questions.add(new Question("Cosmology",  "What does the strong nuclear force control?", "It attracts all matter to each other", "It holds protons and neutrons in nuclei together", "It holds electrons in orbit in atoms", "Radioactive decay", "It holds protons and neutrons in nuclei together"));
        Questions.add(new Question("Cosmology",  "What percentage of the universe do scientists predict dark energy to occupy?", "90%", "38%", "68%", "52%", "68%"));
        Questions.add(new Question("Cosmology",  "What is NOT true about the big bang?", "It started 13.7 billion years ago", "At one point space was moving faster than light itself", "The 4 fundamental forces were combined together at the start", "The speed of light was reduced at the beginning", "The speed of light was reduced at the beginning"));
        Questions.add(new Question("Cosmology",  "What is a singularity?", "A zone of infinite pressure, and density that is infinitely small and hot", "No one knows", "A zone of specific pressure and density", "A zone of low pressure and density that is small and hot", "A zone of infinite pressure, and density that is infinitely small and hot"));











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