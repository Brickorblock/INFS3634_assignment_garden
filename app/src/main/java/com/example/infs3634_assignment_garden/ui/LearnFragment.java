package com.example.infs3634_assignment_garden.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.TopicsAdapter;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;
import com.example.infs3634_assignment_garden.entities.Topics;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LearnFragment extends Fragment implements TopicsAdapter.LaunchListener {
    public static final String KEY_TOPIC = "LearnFragment_Topic";

   public ArrayList<Quiz> quizzes = Garden.getQuizzes();

   public ArrayList<Topics> TopicList = Topics.createTopicsfromQuizzes(quizzes);
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_learn, container, false);
        Log.d("learn fragment", "quizzes: " + quizzes + quizzes.size());
        Log.d("learn fragment", "topics: " + TopicList + TopicList.size());
        myRecyclerView = root.findViewById(R.id.learnRecycler);
        myRecyclerView.setHasFixedSize(true);
        myAdapter = new TopicsAdapter(TopicList, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(myAdapter);



        return root;




    }

    @Override
    public void launch(int position) {

        String topic = getTopic(position);

        Bundle intentBundle = new Bundle();
        intentBundle.putString(KEY_TOPIC, topic);
        MainActivity.navController.navigate(R.id.action_navigation_learn_to_chapterFragment, intentBundle);

    }

    public String getTopic(int position) {

        Topics chosentopic = TopicList.get(position);

        String topic = chosentopic.getTopic();

        Log.d("main activity", "position: " + position);


        Log.d("main activity", "chosenquiz: " + chosentopic);


        return topic;
    }
}
