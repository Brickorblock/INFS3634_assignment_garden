package com.example.infs3634_assignment_garden.entities;

import android.util.Log;

import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.subclasses.AppleTree;
import com.example.infs3634_assignment_garden.entities.subclasses.Evergreen;
import com.example.infs3634_assignment_garden.entities.subclasses.LemonTree;
import com.example.infs3634_assignment_garden.entities.subclasses.OrangeTree;

import java.util.ArrayList;

import static com.example.infs3634_assignment_garden.MainActivity.garden;

public class Helper {
    //helper class with various helper methods

    public static void calcAllGrowth(ArrayList<Plant> plantList){
        // calls calcGrowth for all plants currently in the ArrayList
        for (int i = 0; i < plantList.size(); i++) {
            plantList.get(i).calcGrowth();
        }

    }

    //sets all the plant indexes for a provided list.
    public static void setPlantIndexes(ArrayList<Plant> plantList){
        for (int i = 0; i < plantList.size(); i++) {
            Plant currPlant = plantList.get(i);

            if (currPlant.getPlantIndex() == -1) {
                currPlant.setPlantIndex(garden.plantIndexSearch(currPlant));
            } else {
                continue;
            }
        }
    }

    //since we aren't storing plantImages (array) in db, we have to populate this manually,
    // from the code, each time the app is launched
    public static void populatePlantImages(ArrayList<Plant> plantList){
        Log.d("TAG", "populatePlantImages: called - size = " + plantList.size());
        for (int i = 0; i < plantList.size(); i++) {

            Plant currPlant = plantList.get(i);
            Log.d("TAG", "populatePlantImages: checking " + currPlant.toString());
            if (currPlant.getName().equals("Evergreen")) {
                Log.d("TAG", "populatePlantImages: in evergreen");
                currPlant.setPlantImages(Evergreen.getSubclassImages());
            } else if (currPlant.getName().equals("Orange Tree")) {
                currPlant.setPlantImages(OrangeTree.getSubclassImages());
            } else if (currPlant.getName().equals("Lemon Tree")) {
                currPlant.setPlantImages(LemonTree.getSubclassImages());
            } else if (currPlant.getName().equals("Apple Tree")) {
                currPlant.setPlantImages(AppleTree.getSubclassImages());
            }
        }
    }
}
