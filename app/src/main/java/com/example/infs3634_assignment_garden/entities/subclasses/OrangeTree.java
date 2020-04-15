package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class OrangeTree extends Plant {
    public OrangeTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Orange Tree");
        super.setTopic("Cosmology");
        int[] plantImages = {
                R.drawable.orangetree0,
                R.drawable.orangetree1,
                R.drawable.orangetree2,
                R.drawable.orangetree3};

        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(2);

    }
}
