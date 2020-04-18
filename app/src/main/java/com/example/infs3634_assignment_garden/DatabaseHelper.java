package com.example.infs3634_assignment_garden;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Question;

//plant table, questions table

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "garden_database.db";
    public static String TABLE_PLANT = "plant";
    public static String TABLE_GARDEN = "garden";
    public static String TABLE_QUESTION = "questions";
    public static String plantCol_1 = "name";
    public static String plantCol_2 = "topic";
    public static String plantCol_3= "growthTotal";
    public static String plantCol_4= "growthLvl";
    public static String plantCol_5= "growthProgress";
    public static String gardenCol_1 = "coin";
    public static String gardenCol_2 = "ambienceLvl";
    public static String questionCol_1 = "topic";
    public static String questionCol_2 = "question";
    public static String questionCol_3 = "choice1";
    public static String questionCol_4 = "choice2";
    public static String questionCol_5 = "choice3";
    public static String questionCol_6 = "choice4";
    public static String questionCol_7 = "answer";


    final String CREATE_TABLE_QUESTION = "CREATE TABLE " +
            TABLE_QUESTION + " ( " +
            questionCol_1 + " TEXT, " +
            questionCol_2 + " TEXT, " +
            questionCol_3 + " TEXT, " +
            questionCol_4 + " INTEGER, " +
            questionCol_5 + " INTEGER, " +
            questionCol_6 + " INTEGER, " +
            questionCol_7 + " INTEGER"  +
            ")";

    final String CREATE_TABLE_PLANT = "CREATE TABLE " +
            TABLE_PLANT + " ( " +
            plantCol_1 + " TEXT, " +
            plantCol_2 + " TEXT, " +
            plantCol_3 + " INTEGER, " +
            plantCol_4 + " INTEGER, " +
            plantCol_5 + " INTEGER " +
            ")";

    final String CREATE_TABLE_GARDEN = "CREATE TABLE " +
            TABLE_GARDEN + " ( " +
            gardenCol_1 + " INTEGER, " +
            gardenCol_2 + " INTEGER"  +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_PLANT);
        db.execSQL(CREATE_TABLE_GARDEN);
        fillQuestions();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PLANT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GARDEN);
        onCreate(db);
    }

    public boolean insertGarden(Garden garden){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gardenValues = new ContentValues();
        gardenValues.put(gardenCol_1, garden.getCoins());
        gardenValues.put(gardenCol_2, garden.getAmbienceLvl());

        db.insert(TABLE_GARDEN,null , gardenValues);

        return true;
    }


    public boolean insertPlant(Plant plant){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues plantValues = new ContentValues();
            plantValues.put(plantCol_1, plant.getName());
            plantValues.put(plantCol_2, plant.getTopic());
            plantValues.put(plantCol_3, plant.getGrowthTotal());
            plantValues.put(plantCol_4, plant.getGrowthLvl());
            plantValues.put(plantCol_5, plant.getGrowthProgress());

            db.insert(TABLE_PLANT,null ,plantValues);
            return true;
    }
    public boolean insertQuestions(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues questionValues = new ContentValues();
        questionValues.put(questionCol_1, question.getTopic());
        questionValues.put(questionCol_2, question.getQuestion());
        questionValues.put(questionCol_3, question.getChoice1());
        questionValues.put(questionCol_4, question.getChoice2());
        questionValues.put(questionCol_5, question.getChoice3());
        questionValues.put(questionCol_6, question.getChoice4());
        questionValues.put(questionCol_7, question.getAnswer());

        db.insert(TABLE_QUESTION,null ,questionValues);

        return true;
    }
    private void fillQuestions(){

        Question q1 = new Question("Solar Systems", "Which of the following is an example of a celestial body?", "Sun", "Moon", "Stars", "All of the Above", "All of the Above");
        insertQuestions(q1);
        Question q2 = new Question("Solar Systems", "Which of the following is an example of a celestial body?", "Sun", "Moon", "Stars", "All of the Above", "All of the Above");
        insertQuestions(q2);
        Question q3 = new Question("Solar Systems", "Which planet has rings around it?", "Jupiter", "Saturn", "Uranus", "All of the Above", "All of the Above");
        insertQuestions(q3);
        Question q4 = new Question("Solar Systems", "The Sun is ___ million km away from the Earth.", "100", "150", "200", "250", "150");
        insertQuestions(q4);
        Question q5 = new Question("Solar Systems", "The diameter of Moon is ______ that of the earth.", "1/2", "1/3", "1/4", "2/3", "1/4");
        insertQuestions(q5);
        Question q6 = new Question("Solar Systems", "The moon moves around the earth in about _____ days.", "11", "33", "60", "27", "27");
        insertQuestions(q6);
        Question q7 = new Question("Solar Systems", "The moon takes _____ days to complete one spin.", "27", "35", "16", "9", "27");
        insertQuestions(q7);
        Question q8 = new Question("Solar Systems", "The majority of asteroids are found between the orbits of:", "Mars and Jupiter", "Earth and Mars", "Jupiter and Saturn", "Saturn and Uranus", "Mars and Jupiter");
        insertQuestions(q8);
        Question q9 = new Question("Solar Systems", "Which planet takes longest time for one spin on its axis?", "Venus", "Mercury", "Saturn", "Uranus", "Mercury");
        insertQuestions(q9);
        Question q10 = new Question("Solar Systems", "Which planet takes longest time to orbit around the Sun?", "Neptune", "Mercury", "Saturn", "Uranus", "Neptune");
        insertQuestions(q10);
        Question q11 = new Question("Solar Systems", "Which is the most abundant in Venus’ atmosphere?", "Nitrogen", "Carbon Dioxide", "Oxygen", "Ozone", "Carbon Dioxide");
        insertQuestions(q11);
        Question q12 = new Question("Solar Systems", "Which of the following is true of our solar system?", "Not all planets orbit the sun in the same direction", "Not all planets spin in the same direction as they orbit", "It has an equal number of stars and planet", "It is less than 1 million years old", "Not all planets spin in the same direction as they orbit");
        insertQuestions(q12);
        Question q13 = new Question("Solar Systems", "Venus is most similar in mass and density to which solar system object?", "Jupiter", "Earth", "Mars", "Phobos", "Earth");
        insertQuestions(q13);
        Question q14 = new Question("Solar Systems", "Which of the following is NOT a dwarf planet?", "Pluto", "Charon", "Eris", "Ceres", "Charon");
        insertQuestions(q14);
        Question q15 = new Question("Solar Systems", "When did the solar system form?", "3.2 billion years ago", "10.8 billion years ago", "7.5 billion years ago", "4.6 billion years ago", "4.6 billion years ago");
        insertQuestions(q15);
        Question q16 = new Question("Solar Systems", "Which of the following is an asteroid?", "Eris", "Charon", "Vesta", "Pluto", "Vesta");
        insertQuestions(q16);
        Question q17 = new Question("Solar Systems", "A greenhouse gas traps ____ in the atmosphere", "Ozone", "Gamma rays", "Heat", "Ultraviolet radiation", "Heat");
        insertQuestions(q17);
        Question q18 = new Question("Solar Systems", "Which is the reason why Venus is hotter than Mercury?", "It’s closer to the sun", "It’s size", "The greenhouse effect", "Climate change", "The greenhouse effect");
        insertQuestions(q18);
        Question q19 = new Question("Solar Systems", "Which moon on Saturn is considered to have the best chance at hosting life?", "Enceladus", "Mimas", "Rhea", "Phoebe", "Enceladus");
        insertQuestions(q19);
        Question q20 = new Question("Solar Systems", "What is the main component of Venus’ clouds?", "Water vapour", "Sulphuric acid", "Methane", "Carbon dioxide", "Sulphuric acid");
        insertQuestions(q20);
        Question q21 = new Question("Solar Systems", "Name the molecule or element that is part of the current atmosphere but would not be in the original atmosphere of the Earth:", "Carbon Dioxide", "Oxygen", "Methane", "Nitrogen", "Oxygen");
        insertQuestions(q21);

        Question q22 = new Question("Cosmology", "The expansion age of the universe is about _________", "10.2 billion years", "13.7 billion years", "5.3 billion years", "16.9 billion years", "13.7 billion years");
        insertQuestions(q22);
        Question q23 = new Question("Cosmology", "The rate of expansion of the universe is __________", "Decelerating", "Accelerating", "Constant", "Zero", "Accelerating");
        insertQuestions(q23);
        Question q24 = new Question("Cosmology", "What type of galaxy shape is the milky way?", "Elliptical", "Irregular", "Spiral", "Barred spiral", "Spiral");
        insertQuestions(q24);
        Question q25 = new Question("Cosmology", "What can be said about the location of the centre of our expanding universe?", "The Earth is at the center", "The Sun is at the center", "the Milky Way Galaxy is at the center", "The universe does not have a center", "The universe does not have a center");
        insertQuestions(q25);
        Question q26 = new Question("Cosmology", "How are galaxies important to our existence?", "Without galaxies, the universe could not be expanding", "Without galaxies, there could not have been a Big Bang", "Galaxies recycle elements produced in stars into future star generations", "Galaxies provide the gravity that prevents us from falling off the earth", "Galaxies recycle elements produced in stars into future star generations");
        insertQuestions(q26);
        Question q27 = new Question("Cosmology", "Galaxies that move closer to us show a what shift?", "Red Shift", "Green Shift", "Orange Shift", "Blue Shift", "Red Shift");
        insertQuestions(q27);
        Question q28 = new Question("Cosmology", "In relation to us, most galaxies are…", "Moving away from us", "Moving toward us", "Not moving", "Moving perpendicular to us", "Moving away from us");
        insertQuestions(q28);
        Question q29 = new Question("Cosmology", "Why is the CMB so cool now?", "The expansion of the universe has cooled the radiation", "Dense clouds of dust have blocked most of it", "The cosmological constant has cooled it", "Hydrogen atoms have condensed on it and chilled it", "The expansion of the universe has cooled the radiation");
        insertQuestions(q29);
        Question q30 = new Question("Cosmology", "Which piece of evidence supports the Big Bang theory?", "The more distant galaxies are moving the slowest", "The more distant galaxies are moving the quickest", "The more distant galaxies are moving towards us", "Galaxies and space is not changing", "The more distant galaxies are moving the quickest");
        insertQuestions(q30);
        Question q31 = new Question("Cosmology", "The \"afterglow\" of the energy from the origin of the universe that we see today", "Sunlight", "Cosmic explosions", "Cosmic background radiation", "Doppler effect", "Cosmic background radiation");
        insertQuestions(q31);
        Question q32 = new Question("Cosmology", "Who discovered that the galaxies were moving away from one another?", "Issac Newton", "Albert Einstein", "Galileo Galile", "Edwin Hubble", "Edwin Hubble");
        insertQuestions(q32);
        Question q33 = new Question("Cosmology", "What happens if you reverse the galaxy motion and go back in time?", "Everything must have been condensed into a singularity", "Everything would still be where it is today", "Some things would remain, some would have moved back", "Nobody has a clue", "Everything must have been condensed into a singularity");
        insertQuestions(q33);
        Question q34 = new Question("Cosmology", "What happened to the radiation emitted from the Big Bang?", "It is visible in telescopes", "It has stretched out to the microwave part of the spectrum", "It has moved to the gamma ray part of the spectrum", "It is too old and no longer exists", "It has stretched out to the microwave part of the spectrum");
        insertQuestions(q34);
        Question q35 = new Question("Cosmology", "What is the universe made up of?", "Energy", "Matter/dark matter", "Spacetime", "All of the above", "All of the above");
        insertQuestions(q35);
        Question q36 = new Question("Cosmology", "Hubble's law states which of the following?", "We live in an expanding universe", "Galaxies far away look redder through a telescope", "The further away a galaxy is, the faster it is receding", "All of these", "All of these");
        insertQuestions(q36);
        Question q37 = new Question("Cosmology",  "The first particles of matter included which of the following?", "Electrons", "Helium atoms", "Carbon atoms", "All of these", "Electron");
        insertQuestions(q37);
        Question q38 = new Question("Cosmology",  "What does the strong nuclear force control?", "It attracts all matter to each other", "It holds protons and neutrons in nuclei together", "It holds electrons in orbit in atoms", "Radioactive decay", "It holds protons and neutrons in nuclei together");
        insertQuestions(q38);
        Question q39 = new Question("Cosmology",  "What percentage of the universe do scientists predict dark energy to occupy?", "90%", "38%", "68%", "52%", "68%");
        insertQuestions(q39);
        Question q40 =  new Question("Cosmology",  "What is NOT true about the big bang?", "It started 13.7 billion years ago", "At one point space was moving faster than light itself", "The 4 fundamental forces were combined together at the start", "The speed of light was reduced at the beginning", "The speed of light was reduced at the beginning");
        insertQuestions(q40);
        Question q41 =  new Question("Cosmology",  "What is a singularity?", "A zone of infinite pressure, and density that is infinitely small and hot", "No one knows", "A zone of specific pressure and density", "A zone of low pressure and density that is small and hot", "A zone of infinite pressure, and density that is infinitely small and hot");
        insertQuestions(q41);

    }

}
