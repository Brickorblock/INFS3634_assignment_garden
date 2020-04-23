package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.entities.subclasses.AppleTree;
import com.example.infs3634_assignment_garden.entities.subclasses.Evergreen;
import com.example.infs3634_assignment_garden.entities.subclasses.LemonTree;
import com.example.infs3634_assignment_garden.entities.subclasses.OrangeTree;

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

    public static ArrayList<ShopItem> getShopItems() {

        ArrayList<ShopItem> ShopThings = new ArrayList<>();

        ShopThings.add(new ShopItem(new Evergreen(true), 100, 0));
        ShopThings.add(new ShopItem(new LemonTree(true), 200, 1));
        ShopThings.add(new ShopItem(new OrangeTree(true), 400, 2));
        ShopThings.add(new ShopItem(new AppleTree(true), 600, 3));

        return ShopThings;
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
