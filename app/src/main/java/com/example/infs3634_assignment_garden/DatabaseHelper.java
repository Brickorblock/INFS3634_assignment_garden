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
    }

}
