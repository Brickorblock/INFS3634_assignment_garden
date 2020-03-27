package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> {
    private LaunchListener mLaunchListener;
    private ArrayList<Plant> dataSet;

    public PlantAdapter(ArrayList<Plant> dataset, LaunchListener launchListener) {
        this.dataSet = dataset;
        this.mLaunchListener = launchListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView plantImage;
        LaunchListener launchListener;

        public MyViewHolder(@NonNull View itemView, LaunchListener launchListener) {
            super(itemView);
            this.launchListener = launchListener;

            this.plantImage = itemView.findViewById(R.id.plantImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "onClick: a plant was clicked");
            launchListener.launch(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plant_itemview, parent, false);

        MyViewHolder vh = new MyViewHolder(v, mLaunchListener);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Plant curr = dataSet.get(position);

        holder.plantImage.setImageResource(curr.getPlantImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface LaunchListener{
        void launch(int position);
    }
}
