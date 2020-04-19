package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;

public class ResultFragment extends Fragment {
    // base values used to calculate rewards
    private static final int COINS_BASE = 10;
    private static final int EXP_BASE = 10;

    private int score;
    private int plantIndex;
    private Plant plant;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle bundle = getArguments();
        score = bundle.getInt(QuestionFragment.KEY_SCORE);
        plantIndex = bundle.getInt(QuestionFragment.KEY_PLANT);
        plant = Garden.plantSearch(plantIndex);

        TextView congratsText = root.findViewById(R.id.congratsText);
        TextView scoreText = root.findViewById(R.id.scoreText);
        TextView coinsText = root.findViewById(R.id.coinsText);
        TextView expText = root.findViewById(R.id.expText);
        TextView lvlupText = root.findViewById(R.id.lvlupText);
        ImageView plantImage = root.findViewById(R.id.topicImage);
        ProgressBar oldPlantBar = root.findViewById(R.id.oldPlantBar);
        ProgressBar newPlantBar = root.findViewById(R.id.newPlantBar);
        Button okButton = root.findViewById(R.id.okButton);
        Log.d("ResultFragment", "In Result Fragment!");

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to home page (Garden Fragment)
                Garden.calcAmbience();
                MainActivity.navController.navigate(R.id.action_resultFragment_to_navigation_garden);
            }
        });

        scoreText.setText(Integer.toString(score));

        // set congrats text
        double scorePercent = score / (double) Quiz.QUESTION_SIZE;
        Log.d("TAG", "onCreateView: scorePercent = " + scorePercent);
        if (scorePercent >= 0.8) {
            congratsText.setText("Fantastic!");
        } else if (scorePercent >= 0.5) {
            congratsText.setText("Well Done!");
        } else {
            congratsText.setText("Good Try!");
        }

        //calc and set coins
        coinsText.setText(Integer.toString(calcCoinsReward()));

        //calculate & set exp related fields
        // old = lvl/exp progress before calculation
        int oldLvl = plant.getGrowthLvl();
        double oldGrowthProgress = plant.getGrowthProgress();
        int expReward = calcExpReward();
        int newLvl = plant.getGrowthLvl();
        double newGrowthProgress = plant.getGrowthProgress();

        plantImage.setImageResource(plant.getPlantImage());

        //set exp text - format string to get dynamic fields
        String expString = getResources().getString(R.string.exp_result_text);
        expString = String.format(expString, plant.getName(), expReward);
        expText.setText(expString);

        //set progress bars
        oldPlantBar.setProgress((int) oldGrowthProgress);
        newPlantBar.setProgress((int) newGrowthProgress);

        //set visibility of lvlupText
        if (oldLvl == 3) {
            //level max
            lvlupText.setText("LEVEL MAX!");
            lvlupText.setVisibility(View.VISIBLE);
        } else if (oldLvl == newLvl) {
            //did not lvl up
            lvlupText.setVisibility(View.INVISIBLE);
        } else {
            //levlled up
            lvlupText.setText("LEVEL UP!");
            lvlupText.setVisibility(View.VISIBLE);
        }

        return root;
    }

    private int calcCoinsReward() {
        //rarity will influence rewards e.g:
        // plant with rarity = 0 will have multiplier of x1
        // plant with rarity = 1 will have multiplier of x1.1
        double rarityMultiplier;
        if (plant.getRarity() == 0) {
            rarityMultiplier = 1;
        } else {
            rarityMultiplier = (plant.getRarity()/(double) 10) + 1;
        }
        double coinsReward = COINS_BASE * score * rarityMultiplier;

        //round amt to nearest int
        int coinsRewardRounded = (int) Math.round(coinsReward);
        //add coins
        Garden.addCoins(coinsRewardRounded);

        return coinsRewardRounded;
    }

    private int calcExpReward() {
        //rarity will influence rewards e.g:
        // plant with rarity = 0 will have multiplier of x1
        // plant with rarity = 1 will have multiplier of x1.1
        double rarityMultiplier;
        if (plant.getRarity() == 0) {
            rarityMultiplier = 1;
        } else {
            rarityMultiplier = (plant.getRarity()/(double) 10) + 1;
        }
        double expReward = EXP_BASE * score * rarityMultiplier;
        //round amt to nearest int
        int expRewardRounded = (int) Math.round(expReward);
        //add exp
        plant.addGrowth(expRewardRounded);

        return expRewardRounded;
    }


}
