package com.example.infs3634_assignment_garden.entities.subclasses;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;

public class Evergreen extends Plant {
    public Evergreen(Boolean quizReady) {
        super(quizReady);

        super.setName("Evergreen");
        super.setTopic("Solar Systems");
        int[] plantImages = {
                R.drawable.evergreen0,
                R.drawable.evergreen1,
                R.drawable.evergreen2,
                R.drawable.evergreen3};

        super.setPlantImages(plantImages);
        super.calcGrowth();
        super.setRarity(0);

    }
}
