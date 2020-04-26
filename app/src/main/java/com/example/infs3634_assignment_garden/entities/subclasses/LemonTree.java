package com.example.infs3634_assignment_garden.entities.subclasses;

import android.util.Log;

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

    public static int[] getSubclassImages() {
        Log.d("TAG", "getPlantImages: calling overridden GET");
        return plantImages;
    }
}
