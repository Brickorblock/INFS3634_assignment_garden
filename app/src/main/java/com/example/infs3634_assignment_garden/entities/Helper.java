package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.entities.Plant;

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



}
