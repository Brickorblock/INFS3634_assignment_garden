package com.example.infs3634_assignment_garden.ui;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.R;

public class PlantDetailFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        final ConstraintLayout root = getView().findViewById(R.id.root);

        ImageView plantImage = getView().findViewById(R.id.plantImage);
        ProgressBar plantBar = getView().findViewById(R.id.plantBar);
        TextView nameText = getView().findViewById(R.id.nameText);
        TextView lvlText = getView().findViewById(R.id.lvlText);
        TextView subjectText = getView().findViewById(R.id.subjectText);
        TextView quizText = getView().findViewById(R.id.quizText);
        Button acceptButton = getView().findViewById(R.id.acceptButton);
        ImageView closeIcon = getView().findViewById(R.id.closeIcon);

        //get bundle
        int positionClicked = getArguments().getInt(GardenFragment.key1);
        Log.d("TAG", "onActivityCreated: positionClicked = " + positionClicked);

        //set plant detail fields
        Plant currPlant = Garden.plantSearch(positionClicked);

        plantImage.setImageResource(currPlant.getPlantImage());
        plantBar.setProgress((int) currPlant.getGrowthProgress());
        nameText.setText(currPlant.getName());
        lvlText.setText("(Lvl " + Integer.toString(currPlant.getGrowthLvl()) + ")");
        subjectText.setText(currPlant.getSubject());
//        quizText.setText(currPlant.getName());

        //set button listeners
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: close clicked!");
                root.setVisibility(View.INVISIBLE);
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
