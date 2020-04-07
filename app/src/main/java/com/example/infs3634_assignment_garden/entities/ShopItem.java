package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;

public class ShopItem {
    Plant plant;
    int cost;
    int levelRequirement;

    public ShopItem(Plant plant, int cost, int levelRequirement) {
        this.plant = plant;
        this.cost = cost;
        this.levelRequirement = levelRequirement;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    //TEMPORARY FOR TESTING AND DE-BUGGING
    public static ArrayList<ShopItem> getTempShopItem() {

        ArrayList<ShopItem> ShopThings = new ArrayList<>();

        ShopThings.add(new ShopItem(new Plant(R.drawable.tree_sample, "Evergreen", "Solar Systems"), 100, 1));
        ShopThings.add(new ShopItem(new Plant(R.drawable.tree_sample, "Evergreen", "Micro-Economics"), 100, 1));
        ShopThings.add(new ShopItem(new Plant(R.drawable.tree_sample, "Evergreen", "Innovation"), 100, 1));


        return ShopThings;
    }

    //TEMPORARY FOR TESTING AND DE-BUGGING
    public static ArrayList<ShopItem> createShopItem(ArrayList<ShopItem> shop) {

        ArrayList<ShopItem> ShopThings = getTempShopItem();
        shop.addAll(ShopThings);

        return shop;


    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "plant=" + plant +
                ", cost=" + cost +
                ", levelRequirement=" + levelRequirement +
                '}';
    }
}
