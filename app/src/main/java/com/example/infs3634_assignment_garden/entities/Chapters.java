package com.example.infs3634_assignment_garden.entities;

import java.util.ArrayList;

public class Chapters {

    public String topic;
    public String chapter;

    public Chapters(String topic, String chapter) {
        this.topic = topic;
        this.chapter = chapter;
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


    public static ArrayList<Chapters> getTempChapters() {

        ArrayList<Chapters> ChapterList = new ArrayList<>();

        ChapterList.add(new Chapters("Solar Systems", "Venus Astronomy"));
        ChapterList.add(new Chapters("Solar Systems", "Earth’s formation and history Astronomy"));
        ChapterList.add(new Chapters("Solar Systems", "Planets of the Solar System Astronomy"));
        ChapterList.add(new Chapters("Solar Systems", "Exploration of the Solar System Astronomy"));
        ChapterList.add(new Chapters("Solar Systems", "Dwarf Planets Astronomy"));

        ChapterList.add(new Chapters("Cosmology", "The Big Bang Astronomy"));
        ChapterList.add(new Chapters("Cosmology", "Cosmic Background Radiation Astronomy"));
        ChapterList.add(new Chapters("Cosmology", "Galaxies and their formation Astronomy"));
        ChapterList.add(new Chapters("Cosmology", "Dark matter and dark energy Astronomy"));
        ChapterList.add(new Chapters("Cosmology", "History of cosmology Astronomy"));

        ChapterList.add(new Chapters("Stars", "Star Layers Astronomy"));
        ChapterList.add(new Chapters("Stars", "Star Lifecycle Astronomy"));
        ChapterList.add(new Chapters("Stars", "Black Holes and Quasars Astronomy"));
        ChapterList.add(new Chapters("Stars", "Nebulae Astronomy"));
        ChapterList.add(new Chapters("Stars", "The Sun Astronomy"));

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
