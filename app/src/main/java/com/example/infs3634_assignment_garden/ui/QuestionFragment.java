package com.example.infs3634_assignment_garden.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634_assignment_garden.database.AppDatabase;
import com.example.infs3634_assignment_garden.MainActivity;

import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static com.example.infs3634_assignment_garden.MainActivity.appDatabase;
import static com.example.infs3634_assignment_garden.MainActivity.garden;

public class QuestionFragment extends Fragment {
    public static final String KEY_SCORE = "QuestionFragment_Score";
    public static final String KEY_PLANT = "QuestionFragment_Plant";

    //Setting up the question, buttons views.
    private TextView mScoreView;
    private TextView mQuestionView;
    private TextView mNumberView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    //Having global variables for the answer, score, question number etc.
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int plantIndex;
    private int quizIndex;
    private List<Question> allQuestions;

    private AppDatabase db;

    //setting up array lists as global variables that will be used layer.
    public List<Question> questionBank = new ArrayList<>();
    public ArrayList<Question> randomisedQuestions = new ArrayList<>();
    // public ArrayList<Question> allQuestions = new ArrayList<>();

    public QuestionFragment() {

        //call async task that populates all questions with the select query of every question in the database.
        try {
            this.allQuestions = new insertQuestionsAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Question Fragment", "All Questions" + allQuestions);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        mScoreView = root.findViewById(R.id.Score);
        mNumberView = root.findViewById(R.id.numberText);
        mQuestionView = root.findViewById(R.id.question);
        mButtonChoice1 = root.findViewById(R.id.choice1);
        mButtonChoice2 = root.findViewById(R.id.choice2);
        mButtonChoice3 = root.findViewById(R.id.choice3);
        mButtonChoice4 = root.findViewById(R.id.choice4);
        Bundle bundle = getArguments();
        String topics = bundle.getString(QuizFragment.KEY_TOPIC);
        Log.d("Question Fragment", "topic: " + topics);
        //Grabbing the plant and quiz index from the quiz fragment.
        plantIndex = bundle.getInt(QuizFragment.KEY_PLANT);
        quizIndex = bundle.getInt(QuizFragment.KEY_QUIZ);

        // This loop goes through the entire list and filters for every question that has the topic that was clicked on from the Quiz Fragment
        //As a result, as all topics should have 20 questions, the size of the question bank will always be 20.
        for (int i = 0; i < allQuestions.size(); i++) {

            if (allQuestions.get(i).getTopic().equals(topics)) {

                questionBank.add(allQuestions.get(i));
            }
        }

        //generate a quiz of 10 questions, randomly pulling from the topic quizbank
        // each number generated = a question in the bank

        Log.d("TAG", "question bank: " + questionBank.size());
        Random rand = new Random();
        ArrayList<Integer> pastNums = new ArrayList<>();

        int i = 0;
        while (i < Quiz.QUESTION_SIZE) {

            int min = 0;
            int max = questionBank.size() - 1;

            int x = rand.nextInt((max - min) + 1) + min;

            //first check if number (i.e. question) is a duplicate
            Boolean duplicateFound = false;
            if (pastNums.size() != 0) {
                for (int j = 0; j < pastNums.size(); j++) {
                    if (pastNums.get(j) == x) {
                        duplicateFound = true;
                        Log.d("TAG", "onCreateView: dupe found!");
                        break;
                    }
                }
            }

            if (duplicateFound) {
                //skip this iteration and re-roll another number
                Log.d("TAG", "REROLLING " + x);
                continue;
            } else {
                Log.d("TAG", "random number: " + x);
                Question newquestion = questionBank.get(x);
                randomisedQuestions.add(newquestion);

                pastNums.add(x);
                i++;
            }
        }

        Log.d("TAG", "randomised questions: " + randomisedQuestions.size());
        //Updating the first question shown.
        updateQuestion();
        //Whenever a choice button is clicked a questions are updated and so are the button choices.
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


    //This method updates the question and button choices.
    private void updateQuestion() {

        Log.d("TAG", "randomised questions:" + randomisedQuestions);
        //If the question number reaches 10, the the quiz is finished.
        if (mQuestionNumber == Quiz.QUESTION_SIZE) {
            endQuiz();

        } else {
            //This grabs the next question in the randomised Questions bank.
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

    //This method just updates the textview containing the score.
    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

    //This method increases the question number whenever a button is clicked, along with calling the update question method to update the question.
    private void setQuestionUpdates(Button mButtonChoice) {
        Log.d("TAG", "setQuestionUpdates: called");
        //increment q number
        mQuestionNumber++;
        //displayQuestionNumber starts counting from 1; but mQuestionNumber starts counting from 0
        int displayQuestionNumber = mQuestionNumber + 1;
        if (mQuestionNumber < Quiz.QUESTION_SIZE) {
            // only continue incrementing up to the last question
            mNumberView.setText("Q" + displayQuestionNumber);
        }

        if (mButtonChoice.getText().equals(mAnswer)) {
            mScore = mScore + 1;
            updateScore(mScore);

            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
        }
        updateQuestion();
    }

    private void endQuiz() {
        // navigate to results fragment on last question
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SCORE, mScore);
        bundle.putInt(KEY_PLANT, plantIndex);
        MainActivity.navController.navigate(
                R.id.action_questionFragment_to_resultFragment, bundle);

        Log.d("TAG", "score: " + mScore);

        //calls task to remove quiz
        new RemoveQuizAsyncTask().execute();

        //generate new quizzes to add to list
        garden.generateQuizzes();

    }
    //inserts list of questions from database
    public class insertQuestionsAsyncTask extends AsyncTask<Void, Void, List<Question>> {

        @Override
        protected List<Question> doInBackground(Void... voids) {

            List<Question> Q1 = appDatabase.questionsDao().getData();

            return Q1;
        }


    }
    //removes this quiz from the quiz list since it's now complete
    private class RemoveQuizAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {

            Bundle bundle = getArguments();
            int quizIndex2 = bundle.getInt(QuizFragment.KEY_QUIZ);
            Log.d("TAG", "Quiz Index 2: " + quizIndex2);

            appDatabase.quizDao().deleteAllQuiz(quizIndex2);

            return garden.getCoins();
        }
    }

}
