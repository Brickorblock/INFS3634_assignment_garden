package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class LemonTree extends Plant {
    private static int[] plantImages = {
            R.drawable.lemontree0,
            R.drawable.lemontree1,
            R.drawable.lemontree2,
            R.drawable.lemontree3};

    public LemonTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Lemon Tree");
        super.setTopic("Stars");
        super.setPlantImages(plantImages);
        super.setRarity(1);
        super.calcGrowth();
    }

    @Override
    public int calcGrowthLvl() {
        int growthLvl = super.getGrowthLvl();

        int plantImage = plantImages[0];

        if (super.getGrowthTotal() >= super.getMilestones()[2]) {
            growthLvl = 3;
            plantImage = plantImages[3];
        } else if (super.getGrowthTotal() >= super.getMilestones()[1]) {
            growthLvl = 2;
            plantImage = plantImages[2];
        } else if (super.getGrowthTotal() >= super.getMilestones()[0]) {
            growthLvl = 1;
            plantImage = plantImages[1];
        }

        super.setPlantImage(plantImage);
        return growthLvl;
    }
}
