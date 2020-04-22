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

import com.example.infs3634_assignment_garden.entities.Chapters;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

//Setting the adapter and data set.
    private ArrayList<Chapters> dataset;
    private LaunchListener mLaunchListener;

    public ChapterAdapter(ArrayList<Chapters> dataset, LaunchListener launchListener) {
        this.dataset = dataset;
        this.mLaunchListener = launchListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView chapterText;
        public ImageView chapterImage;
        LaunchListener launchListener;


        public MyViewHolder(@NonNull View itemView, LaunchListener mLaunchListener) {
            super(itemView);
            this.chapterText = itemView.findViewById(R.id.chapterText);
            this.chapterImage = itemView.findViewById(R.id.topicImage);
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
                .inflate(R.layout.chapter_itemview, parent, false);

        MyViewHolder vh = new MyViewHolder(v, mLaunchListener);

        Log.d("Quiz Adapter", "inflater set");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Chapters chapter = dataset.get(position);
        String chaptername = chapter.getChapter();
        int image = chapter.getChapterImage();

        // parse the topic name before displaying
        String arr[] = chaptername.split(" Astro", 2);
        String finalchaptername = arr[0];

        holder.chapterText.setText(finalchaptername);
        holder.chapterImage.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
