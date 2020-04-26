package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class AppleTree extends Plant {
    private static int[] plantImages = {
            R.drawable.appletree0,
            R.drawable.appletree1,
            R.drawable.appletree2,
            R.drawable.appletree3};

    public AppleTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Apple Tree");
        super.setTopic("Solar Systems");
        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(3);
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
