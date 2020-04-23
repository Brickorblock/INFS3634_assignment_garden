//package com.example.infs3634_assignment_garden;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.infs3634_assignment_garden.entities.Question;
//
//import java.util.List;
//
//// to do add view model reference
////to call view model and call database you
//
//
//public class QuestionsViewModel extends AndroidViewModel {
//
//    private QuestionsRepository repository;
//    private LiveData<List<Question>> allQuestions;
//
//    public QuestionsViewModel(@NonNull Application application) {
//        super(application);
//        repository = new QuestionsRepository(application);
//        allQuestions = repository.getAllQuestion();
//    }
//
//    public void insert(Question question) {
//        repository.insert(question);
//    }
//
//    public LiveData<List<Question>> getAllQuestions() {
//        return allQuestions;
//    }
//
//}
