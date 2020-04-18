package com.example.infs3634_assignment_garden.entities;

import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;

public class Chapters {

    private String topic;
    private String chapter;
    private int chapterImage;

    public Chapters(String topic, String chapter, int chapterImage) {
        this.topic = topic;
        this.chapter = chapter;
        this.chapterImage = chapterImage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getChapterImage() {
        return chapterImage;
    }

    public void setChapterImage(int chapterImage) {
        this.chapterImage = chapterImage;
    }

    public static ArrayList<Chapters> getTempChapters() {

        ArrayList<Chapters> ChapterList = new ArrayList<>();

        ChapterList.add(new Chapters("Solar Systems", "Venus Astronomy", R.drawable.venus));
        ChapterList.add(new Chapters("Solar Systems", "Earth’s formation and history Astronomy", R.drawable.earth));
        ChapterList.add(new Chapters("Solar Systems", "Planets of the Solar System Astronomy", R.drawable.solarsystem));
        ChapterList.add(new Chapters("Solar Systems", "Exploration of the Solar System Astronomy", R.drawable.exploration));
        ChapterList.add(new Chapters("Solar Systems", "Dwarf Planets Astronomy", R.drawable.dwarfplanets));

        ChapterList.add(new Chapters("Cosmology", "The Big Bang Astronomy", R.drawable.bigbang));
        ChapterList.add(new Chapters("Cosmology", "Cosmic Background Radiation Astronomy", R.drawable.cosmicradiation));
        ChapterList.add(new Chapters("Cosmology", "Galaxies and their formation Astronomy", R.drawable.cosmology));
        ChapterList.add(new Chapters("Cosmology", "Dark matter and dark energy Astronomy", R.drawable.darkmatter));
        ChapterList.add(new Chapters("Cosmology", "History of cosmology Astronomy", R.drawable.solarsystem));

        ChapterList.add(new Chapters("Stars", "Star Layers Astronomy", R.drawable.starlayers));
        ChapterList.add(new Chapters("Stars", "Star Lifecycle Astronomy", R.drawable.stars));
        ChapterList.add(new Chapters("Stars", "Black Holes and Quasars Astronomy", R.drawable.blackhole));
        ChapterList.add(new Chapters("Stars", "Nebulae Astronomy", R.drawable.nebula));
        ChapterList.add(new Chapters("Stars", "The Sun Astronomy", R.drawable.sun));

        return ChapterList;

    }

    @Override
    public String toString() {
        return "Chapters{" +
                "topic='" + topic + '\'' +
                ", chapter='" + chapter + '\'' +
                '}';
    }
}
