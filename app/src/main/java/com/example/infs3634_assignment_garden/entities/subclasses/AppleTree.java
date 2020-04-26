package com.example.infs3634_assignment_garden.entities.subclasses;

import android.util.Log;

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

    public static int[] getSubclassImages() {
        Log.d("TAG", "getPlantImages: calling overridden GET");
        return plantImages;
    }
}
