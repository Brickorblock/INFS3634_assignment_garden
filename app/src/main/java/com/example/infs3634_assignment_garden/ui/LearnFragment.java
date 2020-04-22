package com.example.infs3634_assignment_garden.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.TopicsAdapter;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Quiz;
import com.example.infs3634_assignment_garden.entities.Topics;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LearnFragment extends Fragment implements TopicsAdapter.LaunchListener {
    public static final String KEY_TOPIC = "LearnFragment_Topic";

    //Create the list of static topics from Garden.
   public static final ArrayList<Topics> TopicList = Garden.getTopics();

   //Set up the recyclerview.
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learn, container, false);
        Log.d("learn fragment", "topics: " + TopicList + TopicList.size());
        //Setting up the recyclerview with the topics list.
        myRecyclerView = root.findViewById(R.id.learnRecycler);
        myRecyclerView.setHasFixedSize(true);
        myAdapter = new TopicsAdapter(TopicList, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(myAdapter);



        return root;




    }

    //Whenever a topic is clicked, the topic name is sent through a bundle to the chapter fragment
    @Override
    public void launch(int position) {

        String topic = getTopic(position);

        Bundle intentBundle = new Bundle();
        intentBundle.putString(KEY_TOPIC, topic);
       //This action changes the navigation to the chapter fragment.
        MainActivity.navController.navigate(R.id.action_navigation_learn_to_chapterFragment, intentBundle);

    }
//This method uses the clicked position to get the chosen topic name.
    public String getTopic(int position) {

        Topics chosentopic = TopicList.get(position);

        String topic = chosentopic.getTopic();

        Log.d("main activity", "position: " + position);


        Log.d("main activity", "chosenquiz: " + chosentopic);


        return topic;
    }
}
