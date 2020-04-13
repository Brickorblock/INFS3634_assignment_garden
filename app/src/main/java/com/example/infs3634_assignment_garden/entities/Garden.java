package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;

public class Garden {
    //this class encapsulates global information about user's progress, plants, quizzes, etc

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

    public int calcAmbienceLvl(){

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

    public int calcAmbienceTotal(){
        //ambience = sum of each plant's individual growthTotals

        for (int i = 0; i < plants.size(); i++){
            ambienceTotal += plants.get(i).getGrowthTotal();
        }
        Log.d("TAG", "calcAmbienceTotal: ambienceTotal = " + ambienceTotal);

        return ambienceTotal;
    }

    public double calcAmbienceProgress(){
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
        } else if (ambienceLvl >= 0){
            amtNeeded = milestones[0];
            currAmt = ambienceTotal;
        }

        ambienceProgress = (currAmt/ (double) amtNeeded) * 100;
        ambienceProgress = Math.round(ambienceProgress);

        Log.d("TAG", "calcAmbienceProgress: currAmt = " + currAmt + "| ambienceProgress = " +ambienceProgress + "| amtNeeded = " + amtNeeded);

        return ambienceProgress;
    }

    public void calcAmbience(){
        calcAmbienceTotal();
        calcAmbienceLvl();
        calcAmbienceProgress();
    }

    public void addPlant(Plant newPlant){
        // 16 is the max amount of plants you can have in the garden
        if (plants.size() < 16) {
            plants.add(newPlant);
        }
    }

    // searches for a plant in the list based on ArrayList index
    public static Plant plantSearch(int index){
        Plant targetPlant = plants.get(index);

        return targetPlant;

    }

    // searches for the index of specified plant
    public static int plantIndexSearch(Plant targetPlant){
        int index = 0;

        //loop through whole arraylist until reaching specified plant
        int i = 0;
        while (i < plants.size()){
            if (plants.get(i) == targetPlant) {
                index = i;
                break;
            } else {
                i++;
            }
        }

        return index;

    }


    //TODO: this is a temp method for dev debugging
    public void getTempPlants(){
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Solar Systems"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Cosmology"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Stars"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));


        plants.get(0).setGrowthTotal(250);
        plants.get(1).setGrowthTotal(50);
        plants.get(2).setGrowthTotal(400);
        plants.get(3).setGrowthTotal(250);
        plants.get(4).setGrowthTotal(100);
        plants.get(5).setGrowthTotal(1000);
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
