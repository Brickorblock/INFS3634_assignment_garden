package com.example.infs3634_assignment_garden.entities;

import java.util.ArrayList;

public class Chapters {

    public String topic;
    public String chapter;
    public String youtubelink;

    public Chapters(String topic, String chapter, String youtubelink) {
        this.topic = topic;
        this.chapter = chapter;
        this.youtubelink = youtubelink;
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

    public String getYoutubelink() {
        return youtubelink;
    }

    public void setYoutubelink(String youtubelink) {
        this.youtubelink = youtubelink;
    }

    public static ArrayList<Chapters> getTempChapters() {

        ArrayList<Chapters> ChapterList = new ArrayList<>();

        ChapterList.add(new Chapters("Solar Systems", "Venus", "ZFUgy3crCYY"));


        ChapterList.add(new Chapters("Cosmology", "The Big Bang", "wNDGgL73ihY"));

        return ChapterList;

    }

    @Override
    public String toString() {
        return "Chapters{" +
                "topic='" + topic + '\'' +
                ", chapter='" + chapter + '\'' +
                ", youtubelink='" + youtubelink + '\'' +
                '}';
    }
}
