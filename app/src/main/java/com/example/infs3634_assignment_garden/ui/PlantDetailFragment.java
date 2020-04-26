package com.example.infs3634_assignment_garden.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.infs3634_assignment_garden.MainActivity.appDatabase;
import static com.example.infs3634_assignment_garden.MainActivity.garden;

public class PlantDetailFragment extends Fragment {
    Plant currPlant;
    String quizReady;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        final ConstraintLayout root = getView().findViewById(R.id.root);

        ImageView plantImage = getView().findViewById(R.id.topicImage);
        ProgressBar plantBar = getView().findViewById(R.id.oldPlantBar);
        TextView nameText = getView().findViewById(R.id.nameText);
        TextView lvlText = getView().findViewById(R.id.lvlText);
        TextView subjectText = getView().findViewById(R.id.topicText);
        TextView quizText = getView().findViewById(R.id.quizText);
        final Button acceptButton = getView().findViewById(R.id.acceptButton);
        final ImageView closeIcon = getView().findViewById(R.id.closeIcon);

        //get bundle
        int positionClicked = getArguments().getInt(GardenFragment.key1);
        Log.d("TAG", "onActivityCreated: positionClicked = " + positionClicked);

        //set plant detail fields
        currPlant = garden.plantSearch(positionClicked);

        plantImage.setImageResource(currPlant.getPlantImage());
        plantBar.setProgress((int) currPlant.getGrowthProgress());
        nameText.setText(currPlant.getName());
        lvlText.setText("(Lvl " + Integer.toString(currPlant.getGrowthLvl()) + ")");
        subjectText.setText(currPlant.getTopic());

        if (currPlant.isQuizReady()) {
            quizText.setText("Quiz Ready!");
            acceptButton.setVisibility(View.VISIBLE);
        } else {
            quizText.setText("Quiz Not Ready");
            acceptButton.setVisibility(View.INVISIBLE);
        }

        //set button listeners
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: close clicked!");
                root.setVisibility(View.INVISIBLE);
            }
        });
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptQuiz();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    //adds corresponding quiz to quiz list
    private void acceptQuiz() {

        try {
            new PopulateQuizTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currPlant.setQuizReady(false);

        new updateQuizReady().execute();

        //refresh the page
        MainActivity.navController.navigate(R.id.navigation_garden);

        Toast.makeText(getActivity(), "Quiz Accepted!", Toast.LENGTH_SHORT).show();
    }

    private class PopulateQuizTask extends AsyncTask<Void, Void, List<Quiz>> {
        @Override
        protected List<Quiz> doInBackground(Void... voids) {

            Quiz quiz2 = new Quiz();

            quiz2.setPlantIndex(garden.plantIndexSearch(currPlant));
            quiz2.setQuestions(Quiz.QUESTION_SIZE);
            quiz2.setTopic(currPlant.getTopic());
            //    MainActivity.appDatabase.quizDao().deleteAllQuiz();
            appDatabase.quizDao().insert2(quiz2);
            Log.d("TAG", "doInBackground: quiz db = " + appDatabase.quizDao().getQuiz());

//            Log.d("TAG", "doInBackground: gardenID in code = " + garden.getId());

            return appDatabase.quizDao().getQuiz();
        }
    }
    //This method changes the quiz ready status of plants to false so that after a quiz is accepted, the quiz ready icon does not appear.
    private class updateQuizReady extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {

            appDatabase.plantDao().updateQuizReady(false, garden.plantIndexSearch(currPlant));

            return null;

        }

    }

}
