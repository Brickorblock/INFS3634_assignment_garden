package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class Plant {
    //TODO: will we need a PlantId?
    private int plantImage;
    private String name;
    private String subject;
    private int growthTotal;
    private int growthLvl;
    private double growthProgress;

    private static int milestone1 = 100;
    private static int milestone2 = 250;
    private static int milestone3 = 400;

    // "growth" = plant EXP (experience)
    //  - growthLvl = the current level of the plant
    //  - growthTotal = total amount of exp plant has
    //  - growthProgress = will be shown in the plant progress bar
    //  - milestones = amount needed for each level


    public Plant(int plantImage, String name, String subject) {
        this.plantImage = plantImage;
        this.name = name;
        this.subject = subject;
        this.growthTotal = 0;
        this.growthLvl = 0;
        this.growthProgress = 0;

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
    // ================================================================

    public void calcGrowth(){
        calcGrowthLvl();
        calcGrowthProgress();
    }
    public double calcGrowthProgress(){
        int amtNeeded = 0;
        int currAmt = 0;

        if (growthLvl == 0) {
            amtNeeded = milestone1;
            currAmt = growthTotal;
        } else if (growthLvl == 1) {
            amtNeeded = milestone2 - milestone1;
            currAmt = growthTotal - milestone1;
        } else if (growthLvl == 2) {
            amtNeeded = milestone3 - milestone2;
            currAmt = growthTotal - milestone2;
        }

        growthProgress = (currAmt/ (double) amtNeeded) * 100;
        growthProgress = Math.round(growthProgress);

        Log.d("TAG", "calcGrowthProgress: currAmt = " + currAmt + "| growthProgress = " + growthProgress + "| amtNeeded = " + amtNeeded);

        return growthProgress;
    }

    public void addGrowth(int growthEXP) {
        this.growthTotal += growthEXP;
    }

    public int calcGrowthLvl(){
        this.growthLvl = 0;

        if (growthTotal >= milestone1) {
            this.growthLvl = 1;
        } else if (growthTotal >= milestone2) {
            this.growthLvl = 2;
        } else if (growthTotal >= milestone3) {
            this.growthLvl = 3;
        }

        return growthLvl;
    }
}
