package com.example.infs3634_assignment_garden.entities.subclasses;

import android.util.Log;

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

    public static int[] getSubclassImages() {
        Log.d("TAG", "getPlantImages: calling overridden GET");
        return plantImages;
    }
}
