package com.example.infs3634_assignment_garden.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;

import static com.example.infs3634_assignment_garden.MainActivity.appDatabase;
import static com.example.infs3634_assignment_garden.MainActivity.garden;

public class ResultFragment extends Fragment {
    // base values used to calculate rewards
    private static final int COINS_BASE = 10;
    private static final int EXP_BASE = 10;

    private int score;
    private int plantIndex;
    private Plant plant;

    private ConstraintLayout coinsConstraint;
    private ConstraintLayout expConstraint;
    private Button okButton;

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
        plant = garden.plantSearch(plantIndex);
        Log.d("TAG", "onCreateView: plant = " + plant.toString());

        TextView congratsText = root.findViewById(R.id.congratsText);
        TextView scoreText = root.findViewById(R.id.scoreText);
        TextView coinsText = root.findViewById(R.id.coinsText);
        TextView expText = root.findViewById(R.id.expText);
        TextView lvlupText = root.findViewById(R.id.lvlupText);
        ImageView plantImage = root.findViewById(R.id.topicImage);
        ProgressBar oldPlantBar = root.findViewById(R.id.oldPlantBar);
        ProgressBar newPlantBar = root.findViewById(R.id.newPlantBar);
        coinsConstraint = root.findViewById(R.id.coinsConstraint);
        expConstraint = root.findViewById(R.id.expConstraint);
        okButton = root.findViewById(R.id.okButton);
        Log.d("ResultFragment", "In Result Fragment!");

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to home page (Garden Fragment)

                garden.calcAmbience();
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
            //levelled up
            lvlupText.setText("LEVEL UP!");
            lvlupText.setVisibility(View.VISIBLE);
        }




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        //render in animations
        Animation coinsAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_up);
        coinsAnim.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 500);
        coinsConstraint.setAnimation(coinsAnim);

        Animation expAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_up);
        expAnim.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 1500);
        expConstraint.setAnimation(expAnim);

        Animation buttonAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        buttonAnim.setStartTime(AnimationUtils.currentAnimationTimeMillis() + 3000);
        okButton.setAnimation(buttonAnim);
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
        garden.addCoins(coinsRewardRounded);
        //calls task to update coin into database
        new UpdateCoinTask().execute();

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
        Log.d("TAG", "calcExpReward: images before addGrowth = "  + plant.getPlantImages()[0]+  " " + plant.getPlantImages()[1] + "...");
        plant.addGrowth(expRewardRounded);
        //calls task to update EXP into database
        new UpdateEXPTask().execute();

        return expRewardRounded;
    }
    private static class UpdateCoinTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {

            appDatabase.gardenDao().updateCoin(garden.getCoins(), garden.getId());

            return garden.getCoins();
        }
    }
    private class UpdateEXPTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {

            appDatabase.plantDao().updateExp(plant.getGrowthTotal(), garden.plantIndexSearch(plant));

            return garden.getCoins();
        }
    }

}
