package com.example.infs3634_assignment_garden;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class QuestionsQuiz extends AppCompatActivity {

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    public ArrayList<Question> currList = new ArrayList<>();
    public ArrayList<Question> Questions = Question.createQuestions(currList);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);

        // String message = intent.getStringExtra(QuizFragment.EXTRA_MESSAGE);
        mScoreView = (TextView)findViewById(R.id.Score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);

        updateQuestion();


        mButtonChoice1.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuestionsQuiz.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuestionsQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuestionsQuiz.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuestionsQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuestionsQuiz.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuestionsQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }
        });


        mButtonChoice4.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if (mButtonChoice4.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuestionsQuiz.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuestionsQuiz.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion(){

        Intent intent = getIntent();
        String topic = intent.getStringExtra(QuizFragment.EXTRA_MESSAGE);

        Random rand = new Random();
        if (topic.equals("Solar Systems")) {

            int min = 0;
            int max = 1;

            int x = ThreadLocalRandom.current().nextInt(min, max+1);

            Question question = Questions.get(x);

            String randomtopic = question.getTopic();

            if(randomtopic.equals(topic)) {

                mQuestionView.setText(question.getQuestion());
                mButtonChoice1.setText(question.getChoice1());
                mButtonChoice2.setText(question.getChoice2());
                mButtonChoice3.setText(question.getChoice3());
                mButtonChoice4.setText(question.getChoice4());

                mAnswer = question.getAnswer();

            }}

        if (topic.equals("Cosmology")) {

            int min = 2;
            int max = 3;

            int x = ThreadLocalRandom.current().nextInt(min, max+1);

            Question question = Questions.get(x);

            String randomtopic = question.getTopic();

            if(randomtopic.equals(topic)) {

                mQuestionView.setText(question.getQuestion());
                mButtonChoice1.setText(question.getChoice1());
                mButtonChoice2.setText(question.getChoice2());
                mButtonChoice3.setText(question.getChoice3());
                mButtonChoice4.setText(question.getChoice4());

                mAnswer = question.getAnswer();

            }}

        if (topic.equals("Stars")) {

            int min = 4;
            int max = 5;

            int x = ThreadLocalRandom.current().nextInt(min, max+1);

            Question question = Questions.get(x);

            String randomtopic = question.getTopic();

            if(randomtopic.equals(topic)) {

                mQuestionView.setText(question.getQuestion());
                mButtonChoice1.setText(question.getChoice1());
                mButtonChoice2.setText(question.getChoice2());
                mButtonChoice3.setText(question.getChoice3());
                mButtonChoice4.setText(question.getChoice4());

                mAnswer = question.getAnswer();

            }}


    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

}

