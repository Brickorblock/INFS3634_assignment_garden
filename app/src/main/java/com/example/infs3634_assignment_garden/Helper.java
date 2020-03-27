package com.example.infs3634_assignment_garden;

import java.util.ArrayList;

public class Helper {
    //helper class with various helper methods

    public static void calcAllGrowth(ArrayList<Plant> plantList){
        // calls calcGrowth for all plants currently in the ArrayList
        for (int i = 0; i < plantList.size(); i++) {
            plantList.get(i).calcGrowth();
        }

    }

}
