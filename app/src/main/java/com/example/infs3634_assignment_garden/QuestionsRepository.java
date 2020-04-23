package com.example.infs3634_assignment_garden;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.infs3634_assignment_garden.entities.Question;

import java.util.List;

public class QuestionsRepository {
    private QuestionsDao questionsDao;
    private LiveData<List<Question>> allQuestion;
    String topic;

    public QuestionsRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        questionsDao = database.questionsDao();

        allQuestion = questionsDao.getAllQuestion(topic);

    }
    public void insert(Question question){
        new InsertQuestionAsyncTask(questionsDao).execute(question);

    }


    public LiveData<List<Question>> getAllQuestion() {
        return allQuestion;
    }

    private static class InsertQuestionAsyncTask extends AsyncTask<Question, Void, Void>{
        private QuestionsDao questionsDao;

        private InsertQuestionAsyncTask(QuestionsDao questionsDao) {
            this.questionsDao = questionsDao;
        }


        @Override
        protected Void doInBackground(Question... questions) {
            questionsDao.insert(questions[0]);
            return null;
        }
    }

}
