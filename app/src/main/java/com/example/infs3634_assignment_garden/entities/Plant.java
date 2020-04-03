package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

public class Plant {
    //TODO: will we need a PlantId?
    private int plantImage;
    private String name;
    private String subject;
    private int growthTotal;
    private int growthLvl;
    private double growthProgress;
    private boolean quizReady;

    private static int[] milestones = {100, 200, 400};

    // "growth" = plant EXP (experience)
    //  - growthLvl = the current level of the plant
    //  - growthTotal = total amount of exp plant has
    //  - growthProgress = will be shown in the plant progress bar
    //  - milestones = amount needed for each level

    //todo implement quiz, multiple plantImages per level


    public Plant(int plantImage, String name, String subject) {
        this.plantImage = plantImage;
        this.name = name;
        this.subject = subject;
        this.growthTotal = 0;
        this.growthLvl = 0;
        this.growthProgress = 0;
        this.quizReady = false;

        calcGrowth();
    }

    public int getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(int plantImage) {
        this.plantImage = plantImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrowthTotal() {
        return growthTotal;
    }

    public void setGrowthTotal(int growthTotal) {
        this.growthTotal = growthTotal;
    }

    public int getGrowthLvl() {
        return growthLvl;
    }

    public void setGrowthLvl() {
        this.growthLvl = growthLvl;
    }

    public void setGrowthLvl(int growthLvl) {
        this.growthLvl = growthLvl;
    }

    public double getGrowthProgress() {
        return growthProgress;
    }

    public void setGrowthProgress(double growthProgress) {
        this.growthProgress = growthProgress;
    }

    public boolean isQuizReady() {
        return quizReady;
    }

    public void setQuizReady(boolean quizReady) {
        this.quizReady = quizReady;
    }

    public static int[] getMilestones() {
        return milestones;
    }

    public static void setMilestones(int[] milestones) {
        Plant.milestones = milestones;
    }

    // ================================================================

    public void calcGrowth(){
        calcGrowthLvl();
        calcGrowthProgress();
    }
    public double calcGrowthProgress(){
        int amtNeeded = 0;
        int currAmt = 0;

        if (growthLvl >= 3) {
            // show progress as max as soon as level 3 (max) is reached
            growthProgress = 100;
            return growthProgress;
        } else if (growthLvl >= 2) {
            amtNeeded = milestones[2] - milestones[1];
            currAmt = growthTotal - milestones[1];
        } else if (growthLvl >= 1) {
            amtNeeded = milestones[1] - milestones[0];
            currAmt = growthTotal - milestones[0];
        } else if (growthLvl >= 0){
            amtNeeded = milestones[0];
            currAmt = growthTotal;
        }

        growthProgress = (currAmt/ (double) amtNeeded) * 100;
        growthProgress = Math.round(growthProgress);

        Log.d("TAG", "calcGrowthProgress: currAmt = " + currAmt + "| growthProgress = " + growthProgress + "| amtNeeded = " + amtNeeded);

        return growthProgress;
    }

    public void addGrowth(int growthEXP) {
        // cannot progress further than than level 3
        if (growthLvl != 3) {
            this.growthTotal += growthEXP;
        }

    }

    public int calcGrowthLvl(){
        this.growthLvl = 0;

        if (growthTotal >= milestones[2]) {
            growthLvl = 3;
        } else if (growthTotal >= milestones[1]) {
            growthLvl = 2;
        } else if (growthTotal >= milestones[0]) {
            growthLvl = 1;
        }

        return growthLvl;
    }
}
