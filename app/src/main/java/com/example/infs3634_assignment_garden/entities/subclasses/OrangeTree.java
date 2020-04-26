package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class OrangeTree extends Plant {
    static int[] plantImages = {
            R.drawable.orangetree0,
            R.drawable.orangetree1,
            R.drawable.orangetree2,
            R.drawable.orangetree3};

    public OrangeTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Orange Tree");
        super.setTopic("Cosmology");
        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(2);

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
