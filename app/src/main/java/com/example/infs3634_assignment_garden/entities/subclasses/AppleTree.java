package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class AppleTree extends Plant {
    public AppleTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Apple Tree");
        super.setTopic("Solar Systems");
        int[] plantImages = {
                R.drawable.appletree0,
                R.drawable.appletree1,
                R.drawable.appletree2,
                R.drawable.appletree3};

        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(3);

    }
}
