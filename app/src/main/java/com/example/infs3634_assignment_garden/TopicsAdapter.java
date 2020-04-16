package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.entities.Topics;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.MyViewHolder> {

    private ArrayList<Topics> dataset;
    private LaunchListener mLaunchListener;

    public TopicsAdapter(ArrayList<Topics> dataset, LaunchListener launchListener) {
        this.dataset = dataset;
        this.mLaunchListener = launchListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView plantImage;
        public TextView topicText;
        LaunchListener launchListener;

        public MyViewHolder(@NonNull View itemView, LaunchListener LaunchListener) {
            super(itemView);
            this.plantImage = itemView.findViewById(R.id.plantImage);
            this.topicText = itemView.findViewById(R.id.topicText);
            this.launchListener = launchListener;

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
                .inflate(R.layout.topic_itemview, parent, false);

        MyViewHolder vh = new MyViewHolder(v, mLaunchListener);

        Log.d("Quiz Adapter", "inflater set");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Topics topic = dataset.get(position);

        String topicname = topic.getTopic();
    //    String arr[] = topicname.split(" Astro", 2);
    //    String finaltopicname = arr[0];

        holder.topicText.setText(topicname);

    }
//ss
    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
