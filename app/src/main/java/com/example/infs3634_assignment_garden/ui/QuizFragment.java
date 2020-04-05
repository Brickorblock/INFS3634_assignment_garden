package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.QuizAdapter;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.ArrayList;

public class QuizFragment extends Fragment implements QuizAdapter.LaunchListener {
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<Quiz> currList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Quiz> myquizzes = Quiz.createQuizzes(currList);

        //todo remove debug code:
        Quiz quiz1 = myquizzes.get(0);
        Plant plant = quiz1.getPlant();

        Log.d("main activity", "myquizzes: " + myquizzes);
        Log.d("main activity", "quiz1: " + quiz1);
        Log.d("main activity", "Plant: " + plant.getName());
        //create recyclerView
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Get a handle to the RecyclerView.
        myRecyclerView = root.findViewById(R.id.quizRecycler);
        myRecyclerView.setHasFixedSize(true);
        // Connect the adapter with the RecyclerView and send all information about clicks to the adapter.
        // Allows for communication between adapter and recylerrview in terrms of clicks.
        myAdapter = new QuizAdapter(myquizzes, this);
        Log.d("main activity", "listenerset");

        // Give the RecyclerView a default layout manager.
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("TAG", "onCreateView: setLayout called");
        // Connect the adapter with the RecyclerView.
        myRecyclerView.setAdapter(myAdapter);

        return root;
    }

    @Override
    public void launch(int position) {

    }
}
