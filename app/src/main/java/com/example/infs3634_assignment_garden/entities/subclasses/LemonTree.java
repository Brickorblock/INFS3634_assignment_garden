package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class LemonTree extends Plant {
    public LemonTree(Boolean quizReady) {
        super(quizReady);

        super.setName("Lemon Tree");
        super.setTopic("Stars");
        int[] plantImages = {
                R.drawable.lemontree0,
                R.drawable.lemontree1,
                R.drawable.lemontree2,
                R.drawable.lemontree3};

        super.setPlantImages(plantImages);
        super.setRarity(1);
        super.calcGrowth();
    }
}
