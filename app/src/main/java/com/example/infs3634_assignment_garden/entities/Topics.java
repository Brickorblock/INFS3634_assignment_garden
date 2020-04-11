package com.example.infs3634_assignment_garden.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Topics {


    public String topic;

    public Topics(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Topics> createTopicsfromQuizzes(ArrayList<Quiz> quizList) {


        ArrayList<String> StringTopicList = new ArrayList<>();

        for(int i = 0; i < quizList.size(); i++) {

            String Topic = quizList.get(i).getTopic();

            StringTopicList.add(Topic);

        }

        ArrayList<Object> listwithoutduplicates = (ArrayList<Object>) StringTopicList.stream().distinct().collect(Collectors.toList());

        ArrayList<Topics> TopicList = Topics.createTopic(listwithoutduplicates);

        return TopicList;

    }

    public static ArrayList<Topics> createTopic(ArrayList<Object> topics) {

        ArrayList<Topics> TopicList = new ArrayList<>();

        for (int i = 0; i < topics.size(); i++) {


            TopicList.add(new Topics (topics.get(i).toString()));

        }

    return TopicList;
    }

    @Override
    public String toString() {
        return "Topic: '"  + topic + '\''
                ;
    }
}



