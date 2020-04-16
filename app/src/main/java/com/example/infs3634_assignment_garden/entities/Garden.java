package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

import com.example.infs3634_assignment_garden.entities.subclasses.Evergreen;

import java.util.ArrayList;
import java.util.Random;

public class Garden {
    //this class encapsulates global information about user's progress, plants, quizzes, etc

    public static final int MAX_PLANTS = 16;

    private static int ambienceLvl;
    private static int ambienceTotal;
    private static double ambienceProgress;
    private static int coins;
    private static ArrayList<Plant> plants;
    private static ArrayList<Quiz> quizzes;
    public static ArrayList<Topics> Topics;

    private static int[] milestones = {500, 1000, 2000};

    // "ambience" = user's total exp
    // - ambienceLvl = what level the user is
    // - ambienceTotal = user's total exp amount
    //  - ambienceProgress = will be shown in the amb progress bar
    //  - milestones = amount needed for each level

    public Garden() {
        this.ambienceLvl = 0;
        this.ambienceTotal = 0;
        this.coins = 0;
        this.plants = new ArrayList<>();
        this.quizzes = new ArrayList<>();
        this.Topics = new ArrayList<>();

        calcAmbience();
    }

    public static int getAmbienceLvl() {
        return ambienceLvl;
    }

    public void setAmbienceLvl(int ambienceLvl) {
        this.ambienceLvl = ambienceLvl;
    }

    public static int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public static void addCoins(int amt) {
        coins += amt;
    }

    public static void deductCoins(int amt) {
        coins -= amt;
    }

    public static ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public static int getAmbienceTotal() {
        return ambienceTotal;
    }

    public void setAmbienceTotal(int ambienceTotal) {
        this.ambienceTotal = ambienceTotal;
    }

    public static double getAmbienceProgress() {
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

    public static ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public static void setQuizzes(ArrayList<Quiz> quizzes) {
        Garden.quizzes = quizzes;
    }

    // ==============================================================

    public static int calcAmbienceLvl() {

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

    public static int calcAmbienceTotal() {
        //ambience = sum of each plant's individual growthTotals

        ambienceTotal = 0;
        for (int i = 0; i < plants.size(); i++) {
            ambienceTotal += plants.get(i).getGrowthTotal();
        }
        Log.d("TAG", "calcAmbienceTotal: ambienceTotal = " + ambienceTotal);

        return ambienceTotal;
    }

    public static double calcAmbienceProgress() {
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

    public static void calcAmbience() {
        calcAmbienceTotal();
        calcAmbienceLvl();
        calcAmbienceProgress();
        Log.d("TAG", "calcAmbience: called - ambienceTotal = " + ambienceTotal);
    }

    public static void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }

    // searches for a plant in the list based on ArrayList index
    public static Plant plantSearch(int index) {
        Plant targetPlant = plants.get(index);

        return targetPlant;

    }

    // searches for the index of specified plant
    public static int plantIndexSearch(Plant targetPlant) {
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

    public static void addQuiz(Quiz newQuiz) {
        quizzes.add(newQuiz);
    }

    public static void removeQuiz(int quizIndex) {
        quizzes.remove(quizIndex);
    }

    //generates quizzes (sets quizReady = true) for 1-3 plants randomly
    // this is how users get more quizzes
    public static int generateQuizzes() {
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
    public static ArrayList<Plant> getPlantsWithQuizzes() {
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
    public static ArrayList<Plant> getPlantsWithoutQuizzes() {
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
        plants.add(new Evergreen(true));
        plants.add(new Evergreen(true));
        plants.add(new Evergreen(true));
        plants.add(new Evergreen(true));



        plants.get(0).setGrowthTotal(250);
        plants.get(1).setGrowthTotal(80);
        plants.get(2).setGrowthTotal(400);
        calcAmbience();
        Helper.calcAllGrowth(plants);
    }

    public static ArrayList<Quiz> getTempQuizzes() {

        quizzes.add(new Quiz(plants.get(0), Quiz.QUESTION_SIZE));
        quizzes.add(new Quiz(plants.get(1), Quiz.QUESTION_SIZE));
        quizzes.add(new Quiz(plants.get(2), Quiz.QUESTION_SIZE));

        return quizzes;
    }

    public static ArrayList<Topics> getTempTopics(){
        Topics.add(new Topics("Solar Systems Astronomy"));
        Topics.add(new Topics("Cosmology Astronomy"));
        Topics.add(new Topics("Stars Astronomy"));
Log.d("Garden", "Topics: " + Topics);
        return Topics;
    }
}
