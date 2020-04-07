package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {
    private ArrayList<Quiz> dataset;
    private LaunchListener mLaunchListener;

    public QuizAdapter(ArrayList<Quiz> dataset, LaunchListener launchListener) {
        this.dataset = dataset;
        this.mLaunchListener = launchListener;
        Log.d("Quiz Adapter", "in constructor");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView plantImage;
        public TextView plantNameText;
        public TextView lvlText;
        public TextView topicText;
        public TextView questionsText;
        LaunchListener launchListener;

        public MyViewHolder(@NonNull View itemView, LaunchListener launchListener) {
            super(itemView);
            this.launchListener = launchListener;
            this.plantImage = itemView.findViewById(R.id.plantImage);
            this.plantNameText = itemView.findViewById(R.id.plantnameText);
            this.lvlText = itemView.findViewById(R.id.lvlText);
            this.topicText = itemView.findViewById(R.id.topicText);
            this.questionsText = itemView.findViewById(R.id.questionsText);
            Log.d("Quiz Adapter", "variables constructed ");
            //todo - code here

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mLaunchListener.launch(getAdapterPosition());
        }
    }

    public interface LaunchListener {
        void launch(int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_itemview, parent, false);

        MyViewHolder vh = new MyViewHolder(v, mLaunchListener);

        Log.d("Quiz Adapter", "inflater set");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quiz quiz = dataset.get(position);

        Plant plant = quiz.getPlant();


        Log.d("Quiz Adapter", "plantname  = " + plant.getName());

        holder.topicText.setText(quiz.getTopic());
        holder.questionsText.setText(quiz.getQuestions());
        holder.plantNameText.setText(plant.getName());
        holder.plantImage.setImageResource(plant.getPlantImage());
        //static for now- need to come back to!!!
        //@ Sudesh - set the levels text like this:
        holder.lvlText.setText("(Lvl " + "3" + ")");

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}