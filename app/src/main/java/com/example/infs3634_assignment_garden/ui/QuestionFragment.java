package com.example.infs3634_assignment_garden.ui;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.QuestionsDao;
import com.example.infs3634_assignment_garden.QuestionsViewModel;
import com.example.infs3634_assignment_garden.R;
import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Question;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.lang.reflect.Field;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private QuestionsViewModel viewModel;

    //setting up array lists as global variables that will be used layer.
    public List<Question> questionBank;
    public ArrayList<Question> randomisedQuestions = new ArrayList<>();
    public ArrayList<Question> allQuestions = new ArrayList<>();

    public QuestionFragment() {
        this.allQuestions = Question.createQuestions(allQuestions);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel  = new ViewModelProvider(this).get(QuestionsViewModel.class);
        viewModel.getAllQuestions().observe(getViewLifecycleOwner(), new Observer<List<Question>>(){

            @Override
            public void onChanged(@Nullable List<Question> questions) {

            }
        });

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
        String topic = bundle.getString(QuizFragment.KEY_TOPIC);
        Log.d("Question Fragment", "topic: " + topic);
        //Grabbing the plant and quix index from the quix fragment.
        plantIndex = bundle.getInt(QuizFragment.KEY_PLANT);
        quizIndex = bundle.getInt(QuizFragment.KEY_QUIZ);

        // This loop goes through the entire list and filters for every question that has the topic that was clicked on from the Quiz Fragment
        //As a result, as all topics should have 20 questions (except stars for now!), the size of the question bank will always be 20.
//        for (int i = 0; i < allQuestions.size(); i++) {
//
//            if (allQuestions.get(i).getTopic().equals(topic)) {
//
//                questionBank.add(allQuestions.get(i));
//            }
//        }


        //call async task that populates question bank with the select query from question dao passing in the topic which is coming from a bundle from quiz
        //database in main activity



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
                    if (pastNums.get(j) == x){
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

        if (mButtonChoice.getText() == mAnswer) {
            mScore = mScore + 1;
            updateScore(mScore);

            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
        }
        updateQuestion();
    }

    private void endQuiz(){
        // navigate to results fragment on last question
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SCORE, mScore);
        bundle.putInt(KEY_PLANT, plantIndex);
        MainActivity.navController.navigate(
                R.id.action_questionFragment_to_resultFragment, bundle);

        Log.d("TAG", "score: " + mScore);

        // also remove this quiz from the quiz list since it's now complete
        Garden.removeQuiz(quizIndex);
        // also generate new quizzes to add to list
        Garden.generateQuizzes();
    }

//    @Override
////    public void onDetach() {
////        super.onDetach();
////
////        try {
////            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
////            childFragmentManager.setAccessible(true);
////            childFragmentManager.set(this, null);
////
////        } catch (NoSuchFieldException e) {
////            throw new RuntimeException(e);
////        } catch (IllegalAccessException e) {
////            throw new RuntimeException(e);
////        }
////    }


}
