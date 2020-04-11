package com.example.infs3634_assignment_garden.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.QuestionsQuiz;
import com.example.infs3634_assignment_garden.QuizAdapter;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.ArrayList;

public class QuizFragment extends Fragment implements QuizAdapter.LaunchListener {

    public static final String KEY_TOPIC = "QuizFragment_Topic";
    public static final String KEY_PLANT = "QuizFragment_Plant";
    private RecyclerView myRecyclerView;
    private TextView noticeText;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<Quiz> currList = new ArrayList<>();

    private Plant plant;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        ArrayList<Quiz> quizzes = Garden.getQuizzes();

        if (quizzes.size() != 0) {
            //todo remove debug code:
            Quiz quiz1 = quizzes.get(0);
            plant = quiz1.getPlant();

            Log.d("main activity", "quizzes: " + quizzes);
            Log.d("main activity", "quiz1: " + quiz1);
            Log.d("main activity", "Plant: " + plant.getName());

            noticeText = root.findViewById(R.id.noticeText);
            noticeText.setVisibility(View.INVISIBLE);

            //create recyclerView
            // Get a handle to the RecyclerView.
            myRecyclerView = root.findViewById(R.id.quizRecycler);
            myRecyclerView.setHasFixedSize(true);

            // Connect the adapter with the RecyclerView and send all information about clicks to the adapter.
            // Allows for communication between adapter and recylerrview in terrms of clicks.
            myAdapter = new QuizAdapter(quizzes, this);
            Log.d("main activity", "listenerset");

            // Give the RecyclerView a default layout manager.
            myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            Log.d("TAG", "onCreateView: setLayout called");
            // Connect the adapter with the RecyclerView.
            myRecyclerView.setAdapter(myAdapter);
        } else {
            noticeText = root.findViewById(R.id.noticeText);
            noticeText.setVisibility(View.VISIBLE);
        }


        return root;
    }

    @Override
    public void launch(int position) {

        Log.d("main activity", "I got clicked!");

        String topic = getTopic(position);

        Log.d("main activity", "topic: ");

        Bundle intentBundle = new Bundle();
        intentBundle.putString(KEY_TOPIC, topic);

        int plantIndex = Garden.plantIndexSearch(plant);
        intentBundle.putInt(KEY_PLANT, plantIndex);
        MainActivity.navController.navigate(R.id.action_navigation_quiz_to_questionFragment, intentBundle);

    }

    public String getTopic(int position) {

        ArrayList<Quiz> myquizzes = Garden.getQuizzes();

        Log.d("main activity", "position: " + position);

        Quiz chosenquiz = myquizzes.get(position);

        Log.d("main activity", "chosenquiz: " + chosenquiz);

        String topic = chosenquiz.getTopic();

        return topic;
    }

}
