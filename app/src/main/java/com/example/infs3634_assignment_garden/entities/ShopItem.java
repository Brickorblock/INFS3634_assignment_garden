package com.example.infs3634_assignment_garden.entities;

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
}
