package com.example.infs3634_assignment_garden.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Topics {

    @PrimaryKey
    private String topic;
    private int image;

    public Topics(String topic, int image) {
        this.topic = topic;
        this.image = image;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Topic: '"  + topic + '\'';
    }
}



