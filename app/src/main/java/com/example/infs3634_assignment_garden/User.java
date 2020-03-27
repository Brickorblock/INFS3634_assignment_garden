package com.example.infs3634_assignment_garden;

import android.util.Log;

import java.util.ArrayList;

public class User {
    //user class encapsulates global information about current User's progress

    private int ambienceLvl;
    private int ambienceTotal;
    private double ambienceProgress;
    private int coins;
    private static ArrayList<Plant> plants;

    private static int milestone1 = 1000;
    private static int milestone2 = 1500;
    private static int milestone3 = 4000;

    // "ambience" = user's total exp
    // - ambienceLvl = what level the user is
    // - ambienceTotal = user's total exp amount
    //  - ambienceProgress = will be shown in the amb progress bar
    //  - milestones = amount needed for each level

    public User() {
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

    public void setAmbienceProgress(int ambienceProgress) {
        this.ambienceProgress = ambienceProgress;
    }

    // ==============================================================

    public int calcAmbienceLvl(){

        ambienceLvl = 0;

        if (ambienceTotal > milestone1) {
            ambienceLvl = 1;
        } else if (ambienceTotal > milestone2) {
            ambienceLvl = 2;
        } else if (ambienceTotal > milestone3) {
            ambienceLvl = 3;
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

        if (ambienceLvl >= 0) {
            amtNeeded = milestone1;
            currAmt = ambienceTotal;
        } else if (ambienceLvl >= 1) {
            amtNeeded = milestone2 - milestone1;
            currAmt = ambienceTotal - milestone1;
        } else if (ambienceLvl >= 2) {
            amtNeeded = milestone3 - milestone2;
            currAmt = ambienceTotal - milestone2;
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
        plants.add(newPlant);
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
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));
        plants.add(new Plant(R.drawable.tree_sample, "Evergreen", "Microeconomics"));

        plants.get(0).setGrowthTotal(200);
        plants.get(1).setGrowthTotal(100);
        calcAmbience();
        Helper.calcAllGrowth(plants);
    }
}
