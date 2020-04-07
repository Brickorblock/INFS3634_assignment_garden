package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;
import com.example.infs3634_assignment_garden.ui.QuizFragment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.example.infs3634_assignment_garden.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    private TextView mScoreView;
    private TextView mQuestionView;
    private TextView mNumberView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public ArrayList<Question> currList = new ArrayList<>();

    public ArrayList<Question> questionBank = new ArrayList<>();

    public ArrayList<Question> randomisedQuestions = new ArrayList<>();

    public ArrayList<Question> Questions = Question.createQuestions(currList);
    public QuestionFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_question2, container, false);
        mScoreView = root.findViewById(R.id.Score);
        mNumberView = root.findViewById(R.id.numberText);
        mQuestionView = root.findViewById(R.id.question);
        mButtonChoice1 = root.findViewById(R.id.choice1);
        mButtonChoice2 = root.findViewById(R.id.choice2);
        mButtonChoice3 = root.findViewById(R.id.choice3);
        mButtonChoice4 = root.findViewById(R.id.choice4);
        Bundle bundle = getArguments();
        String topic = bundle.getString(QuizFragment.EXTRA_MESSAGE);
        Log.d("Question Fragment", "topic: " + topic);

        //the reason i is 42 is because the number of questions in the question class is 42.
        // This loop goes through the entire list and filters for every question that has the topic that was clicked on from the Quiz Fragment
        //As a result, as all topics should have 20 questions (except stars for now!), the size of the question bank will always be 20.

        for (int i = 0; i < 42; i++) {

            if (Questions.get(i).getTopic().equals(topic)) {

                questionBank.add(Questions.get(i));
            }
        }

        Log.d("TAG", "question bank: " + questionBank.size());
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {

            int min = 0;
            int max = 19;

            int x = rand.nextInt((max - min) + min);

            Log.d("TAG", "random number: " + x);

            Question newquestion = questionBank.get(x);

            randomisedQuestions.add(newquestion);
            //todo Double check for duplicate questions

        }

        Log.d("TAG", "randomised questions: " + randomisedQuestions.size());

        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestionUpdates(mButtonChoice1);
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setQuestionUpdates(mButtonChoice2);
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestionUpdates(mButtonChoice3);
            }
        });


        mButtonChoice4.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                setQuestionUpdates(mButtonChoice4);
            }
        });


        // Inflate the layout for this fragment
        return root;
    }





    private void updateQuestion() {

        Log.d("TAG", "randomised questions:" + randomisedQuestions);

        //todo implement intent to new activity if the question number is > 9 here. Pass the score in the intent.

        if (mQuestionNumber > 9) {

            //bundle stuff to new screen showing final score.

            Log.d("TAG", "score: " + mScore);
        } else {

            Question finalquestion = randomisedQuestions.get(mQuestionNumber);

            mQuestionView.setText(finalquestion.getQuestion());
            mButtonChoice1.setText(finalquestion.getChoice1());
            mButtonChoice2.setText(finalquestion.getChoice2());
            mButtonChoice3.setText(finalquestion.getChoice3());
            mButtonChoice4.setText(finalquestion.getChoice4());

            mAnswer = finalquestion.getAnswer();


            Log.d("TAG", "question number: " + mQuestionNumber);
        }
    }







    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }







    private void setQuestionUpdates(Button mButtonChoice) {
        Log.d("TAG", "setQuestionUpdates: called");
        //increment q number
        mQuestionNumber++;
        //displayQuestionNumber starts counting from 1; but mQuestionNumber starts counting from 0
        int displayQuestionNumber = mQuestionNumber + 1;
        mNumberView.setText("Q" + displayQuestionNumber);

        if (mButtonChoice.getText() == mAnswer) {
            mScore = mScore + 1;
            updateScore(mScore);
            updateQuestion();
            //This line of code is optiona
            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
            updateQuestion();
        }
    }
}
