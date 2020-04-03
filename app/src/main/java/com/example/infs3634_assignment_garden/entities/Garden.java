package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;

public class Garden {
    //Garden class encapsulates global information about current user's progress

    private int ambienceLvl;
    private int ambienceTotal;
    private double ambienceProgress;
    private int coins;
    private static ArrayList<Plant> plants;

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


    //TODO: this is a temp method for dev debugging
    public void getTempPlants(){
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
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));


        plants.get(0).setGrowthTotal(255);
        plants.get(1).setGrowthTotal(50);
        plants.get(2).setGrowthTotal(400);
        plants.get(3).setGrowthTotal(250);
        plants.get(4).setGrowthTotal(100);
        plants.get(5).setGrowthTotal(1000);
        calcAmbience();
        Helper.calcAllGrowth(plants);
    }
}
