package com.example.infs3634_assignment_garden.entities.subclasses;

import android.util.Log;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class Evergreen extends Plant {
    private static int[] plantImages = {
            R.drawable.evergreen0,
            R.drawable.evergreen1,
            R.drawable.evergreen2,
            R.drawable.evergreen3};

    public Evergreen(Boolean quizReady) {
        super(quizReady);

        super.setName("Evergreen");
        super.setTopic("Solar Systems");
        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(0);

    }

    public static int[] getSubclassImages() {
        Log.d("TAG", "getPlantImages: calling overridden GET");
        return plantImages;
    }

//    @Override
//    public int calcGrowthLvl() {
//        int growthLvl = super.getGrowthLvl();
//
//        int plantImage = plantImages[0];
//
//        Log.d("TAG", "calcGrowthLvl: growthTotal = " + super.getGrowthTotal() + "milestones = " + super.getMilestones());
//        if (super.getGrowthTotal() >= super.getMilestones()[2]) {
//            growthLvl = 3;
//            plantImage = plantImages[3];
//        } else if (super.getGrowthTotal() >= super.getMilestones()[1]) {
//            growthLvl = 2;
//            plantImage = plantImages[2];
//        } else if (super.getGrowthTotal() >= super.getMilestones()[0]) {
//            growthLvl = 1;
//            plantImage = plantImages[1];
//        }
//
//        super.setPlantImage(plantImage);
//        return growthLvl;
//    }
}
