package com.example.infs3634_assignment_garden.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infs3634_assignment_garden.Plant;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.User;

public class PlantDetailFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ImageView plantImage = getView().findViewById(R.id.plantImage);
        ImageView closeIcon = getView().findViewById(R.id.closeIcon);
        ProgressBar plantBar = getView().findViewById(R.id.plantBar);
        TextView nameText = getView().findViewById(R.id.nameText);
        TextView lvlText = getView().findViewById(R.id.lvlText);
        TextView subjectText = getView().findViewById(R.id.subjectText);
        TextView quizText = getView().findViewById(R.id.quizText);
        Button acceptButton = getView().findViewById(R.id.acceptButton);

        //get bundle
        int positionClicked = getArguments().getInt(GardenFragment.key1);
        Log.d("TAG", "onActivityCreated: positionClicked = " + positionClicked);

        //set plant detail fields
        Plant currPlant = User.plantSearch(positionClicked);

        plantImage.setImageResource(currPlant.getPlantImage());
        plantBar.setProgress((int) currPlant.getGrowthProgress());
        nameText.setText(currPlant.getName());
        lvlText.setText("(Lvl " + Integer.toString(currPlant.getGrowthLvl()) + ")");
        subjectText.setText(currPlant.getSubject());
//        quizText.setText(currPlant.getName());

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}
