package com.example.infs3634_assignment_garden.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_assignment_garden.ChapterAdapter;
import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Chapters;

import java.util.ArrayList;

/**
 */
public class ChapterFragment extends Fragment implements ChapterAdapter.LaunchListener {
    public static final String KEY_ChapterName = "ChapterFragment_ChapterName";
    //setting up recyclerview.
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;


    //Creating the static list of chapters from the chapter class.
    public ArrayList<Chapters> ChapterList = Chapters.getTempChapters();

    //This is a filtered list based on the topic name
    public ArrayList<Chapters> FilteredList = new ArrayList<>();


    public ChapterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chapter, container, false);

//Grabbing the topic name from the topic fragment
        Bundle bundle = getArguments();
        String topic = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            topic = bundle.getString(LearnFragment.KEY_TOPIC);
        }
        Log.d("Chapter Fragment","Chosen Topic: " + topic);

        myRecyclerView = root.findViewById(R.id.chapterRecycler);
        myRecyclerView.setHasFixedSize(true);

//This loop goes through the chapter list that we created and only gets the chapters which have the chosen topic name.
        for (int i = 0; i < ChapterList.size(); i++) {

            String topicname = ChapterList.get(i).getTopic();

            Chapters chapter = ChapterList.get(i);

            if (topicname.equals(topic)) {

                FilteredList.add(chapter);
            }

        }

        Log.d("Chapter Fragment","Filtered List " + FilteredList);

        //adapter is called.

        myAdapter = new ChapterAdapter(FilteredList, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(myAdapter);


        // Inflate the layout for this fragment
        return root;
    }


    //whenever a chapter is clicked a bundle is sent to the video fragment containing the chapter name.
    @Override
    public void launch(int position) {

        String chaptername = getchapter(position);

        Bundle intentBundle = new Bundle();
        intentBundle.putString(KEY_ChapterName , chaptername);
        //The appropriate navigation action is used to switch to the video fragment
        MainActivity.navController.navigate(R.id.action_chapterFragment_to_videosFragment, intentBundle);

    }
//this method gets the chapter name through using the position clicked.
    public String getchapter(int position) {

       Chapters chosenchapter = FilteredList.get(position);

        String chaptername = chosenchapter.getChapter();

        Log.d("main activity", "position: " + position);

        Log.d("main activity", "chosenquiz: " + chaptername);

        return chaptername;
    }
}
