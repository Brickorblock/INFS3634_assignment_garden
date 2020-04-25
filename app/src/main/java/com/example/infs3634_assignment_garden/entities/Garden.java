package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.subclasses.Evergreen;
import com.example.infs3634_assignment_garden.entities.subclasses.LemonTree;
import com.example.infs3634_assignment_garden.entities.subclasses.OrangeTree;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Random;

@Entity
public class Garden {
    //this class encapsulates global information about user's progress, plants, quizzes, etc
    @Ignore
    public static final int MAX_PLANTS = 16;
    @Ignore
    private int ambienceLvl;
    @Ignore
    private int ambienceTotal;
    @Ignore
    private double ambienceProgress;
    @PrimaryKey (autoGenerate = true)
    private int id;
    private int coins;
    @Ignore
    public ArrayList<Plant> plants;
    @Ignore
    private ArrayList<Quiz> quizzes;
    @Ignore
    public ArrayList<Topics> topics;

    @Ignore
    private static int[] milestones = {300, 800, 1500};

    // "ambience" = user's total exp
    // - ambienceLvl = what level the user is
    // - ambienceTotal = user's total exp amount
    //  - ambienceProgress = will be shown in the amb progress bar
    //  - milestones = amount needed for each level

    public Garden() {
        this.ambienceLvl = 0;
        this.ambienceTotal = 0;
        this.coins = 0;
        this.id = 1;
        this.plants = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.topics = new ArrayList<>();

        calcAmbience();
    }

    public int getAmbienceLvl() {
        return ambienceLvl;
    }

    public void setAmbienceLvl(int ambienceLvl) {
        this.ambienceLvl = ambienceLvl;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amt) {
        coins += amt;
    }

    public void deductCoins(int amt) {
        coins -= amt;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public int getAmbienceTotal() {
        return ambienceTotal;
    }

    public void setAmbienceTotal(int ambienceTotal) {
        this.ambienceTotal = ambienceTotal;
    }

    public double getAmbienceProgress() {
        return ambienceProgress;
    }

    public void setAmbienceProgress(double ambienceProgress) {
        this.ambienceProgress = ambienceProgress;
    }

    public static int[] getMilestones() {
        return milestones;
    }

    public static void setMilestones(int[] milestones) {
        Garden.milestones = milestones;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setTopics(ArrayList<Topics> topics) {
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ==============================================================

    public int calcAmbienceLvl() {

        ambienceLvl = 0;

        if (ambienceTotal >= milestones[2]) {
            ambienceLvl = 3;
        } else if (ambienceTotal >= milestones[1]) {
            ambienceLvl = 2;
        } else if (ambienceTotal >= milestones[0]) {
            ambienceLvl = 1;
        }

        Log.d("TAG", "calcAmbienceLvl: ambienceLvl = " + ambienceLvl);

        return ambienceLvl;
    }

    public int calcAmbienceTotal() {
        //ambience = sum of each plant's individual growthTotals

        ambienceTotal = 0;
        for (int i = 0; i < plants.size(); i++) {
            ambienceTotal += plants.get(i).getGrowthTotal();
        }
        Log.d("TAG", "calcAmbienceTotal: ambienceTotal = " + ambienceTotal);

        return ambienceTotal;
    }

    public double calcAmbienceProgress() {
        int amtNeeded = 0;
        int currAmt = 0;

        if (ambienceLvl >= 3) {
            // show progress as max as soon as level 3 (max) is reached
            ambienceProgress = 100;
            return ambienceProgress;
        } else if (ambienceLvl >= 2) {
            amtNeeded = milestones[2] - milestones[1];
            currAmt = ambienceTotal - milestones[1];
        } else if (ambienceLvl >= 1) {
            amtNeeded = milestones[1] - milestones[0];
            currAmt = ambienceTotal - milestones[0];
        } else if (ambienceLvl >= 0) {
            amtNeeded = milestones[0];
            currAmt = ambienceTotal;
        }

        ambienceProgress = (currAmt / (double) amtNeeded) * 100;
        ambienceProgress = Math.round(ambienceProgress);

        Log.d("TAG", "calcAmbienceProgress: currAmt = " + currAmt + "| ambienceProgress = " + ambienceProgress + "| amtNeeded = " + amtNeeded);

        return ambienceProgress;
    }

    public void calcAmbience() {
        calcAmbienceTotal();
        calcAmbienceLvl();
        calcAmbienceProgress();
        Log.d("TAG", "calcAmbience: called - ambienceTotal = " + ambienceTotal);
    }

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }

    // searches for a plant in the list based on ArrayList index
    public Plant plantSearch(int index) {
        Plant targetPlant = plants.get(index);

        return targetPlant;

    }

    // searches for the index of specified plant
    public int plantIndexSearch(Plant targetPlant) {
        int index = 0;

        Log.d("TAG", "plantIndexSearch: searching for - " + targetPlant.toString());

        //loop through whole arraylist until reaching specified plant
        int i = 0;
        while (i < plants.size()) {
            if (plants.get(i) == targetPlant) {
                index = i;
                Log.d("TAG", "plantIndexSearch: FOUND!! " + plants.get(i).toString());
                break;
            } else {
                i++;
            }
        }

        return index;

    }

    public void addQuiz(Quiz newQuiz) {
        quizzes.add(newQuiz);
    }

    public void removeQuiz(int quizIndex) {
        quizzes.remove(quizIndex);
    }

    //generates quizzes (sets quizReady = true) for 1-3 plants randomly
    // this is how users get more quizzes
    public int generateQuizzes() {
        // first RNG how many quizzes to generate (1 - 3)
        Random rand = new Random();
        int generateAmt;
        int amtMin = 1;
        int amtMax = 3;
        generateAmt = rand.nextInt((amtMax - amtMin) + 1) + amtMin;

        // check if the amt to generate will overflow*, and cap the amount accordingly
        // *if the amt to generate is higher than the plants currently w/o quizzes, this cause
        // index out of bounds exception later
        while (generateAmt != 0) {
            // loop to gradually decrease generateAmt until it will fit
            if ((generateAmt + getPlantsWithQuizzes().size()) > plants.size()) {
                generateAmt--;
            } else {
                break;
            }
        }
        Log.d("TAG", "generateQuizzes: generateAmt = " + generateAmt);

        //now RNG the targetPlants that get quizzes generated for them
        // grab a list of all the plants currently without quizzes
        ArrayList<Plant> plantsWithoutQuizzes = getPlantsWithoutQuizzes();

        //perform the RNG using the list plantsWithoutQuizzes
        int i = 0;
        while (i < generateAmt) {
            int targetMin = 0;
            int targetMax = plantsWithoutQuizzes.size() - 1;

            int targetPlantIndex = rand.nextInt(((targetMax - targetMin) + 1) + targetMin);

            // now setQuizReady = true for the randomly generated plant
            Plant targetPlant = plantsWithoutQuizzes.get(targetPlantIndex);
            targetPlant.setQuizReady(true);
            i++;
        }

        return generateAmt;
    }

    //returns the list of plants that currently have quizzes ready
    public ArrayList<Plant> getPlantsWithQuizzes() {
        ArrayList<Plant> plantsWithQuizzes = new ArrayList<>();

        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).isQuizReady()) {
                plantsWithQuizzes.add(plants.get(i));
            }
        }
        Log.d("TAG", "calcPlantsWithQuizzes: amt = " + plantsWithQuizzes.size());
        return plantsWithQuizzes;
    }

    //returns the list of plants that DON'T have quizzes ready
    public ArrayList<Plant> getPlantsWithoutQuizzes() {
        ArrayList<Plant> plantsWithoutQuizzes = new ArrayList<>();

        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).isQuizReady() == false) {
                plantsWithoutQuizzes.add(plants.get(i));
            }
        }
        return plantsWithoutQuizzes;
    }


    //TODO: this is a temp method for dev debugging
    public void getTempPlants() {
        plants.add(new Evergreen(true));
        plants.add(new LemonTree(true));
        plants.add(new OrangeTree(true));

        calcAmbience();
        Helper.calcAllGrowth(plants);
    }

    public ArrayList<Quiz> getTempQuizzes() {

        quizzes.add(new Quiz(plants.get(0), Quiz.QUESTION_SIZE));
        quizzes.add(new Quiz(plants.get(1), Quiz.QUESTION_SIZE));
        quizzes.add(new Quiz(plants.get(2), Quiz.QUESTION_SIZE));
        Log.d("TAG", "getTempQuizzes: quizzes = " + quizzes.toString());
        return quizzes;
    }

    public ArrayList<Topics> getTopics() {
        topics.add(new Topics("Solar Systems", R.drawable.solarsystem));
        topics.add(new Topics("Cosmology", R.drawable.cosmology));
        topics.add(new Topics("Stars", R.drawable.stars));
        Log.d("Garden", "Topics: " + topics);
        return topics;
    }
}
