package com.example.infs3634_assignment_garden;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.entities.Quiz;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private ArrayList<Quiz> dataset;
    private LaunchListener mLaunchListener;

    public ShopAdapter(ArrayList<Quiz> dataset, LaunchListener launchListener) {
        this.dataset = dataset;
        this.mLaunchListener = launchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView plantImage;
        public TextView plantNameText;
        public TextView topicText;
        public TextView questionsText;
        //@Justin - buyButton & requiredLvlConstraint are the two elements that we will
        // swap their visibilities depending on if the user is high enough level
        // both are visible by default; u will have to implement the visibility switcher
        public Button buyButton;
        public ConstraintLayout requiredLvlConstraint;
        public TextView requiredLvlText;

        public MyViewHolder(@NonNull View itemView, LaunchListener launchListener) {
            super(itemView);

            //todo code here

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mLaunchListener.launch(getAdapterPosition());
        }
    }

    public interface LaunchListener{
        void launch(int position);
    }
}
