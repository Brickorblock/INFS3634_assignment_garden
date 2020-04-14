package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.entities.Video.VideoDetails;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoDetails> videoList;
    private LaunchListener mLaunchListener;

    public VideoAdapter(List<VideoDetails> videoList, LaunchListener mLaunchListener) {
        this.videoList = videoList;
        this.mLaunchListener = mLaunchListener;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titlevideo;
        LaunchListener launchListener;

        public VideoViewHolder(@NonNull View itemView, LaunchListener LaunchListener) {
            super(itemView);
            this.titlevideo = itemView.findViewById(R.id.titlevideo);
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
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);

        VideoViewHolder vh = new VideoViewHolder(v, mLaunchListener);

        Log.d("Video Adapter", "inflater set");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        VideoDetails chosenvideo = videoList.get(position);
        Log.d("Video Adapter", "video title: " + chosenvideo.getTitle());
        holder.titlevideo.setText(chosenvideo.getTitle());

    }

    @Override
    public int getItemCount() {

        int size = 0;
                if(videoList != null) {
                    size = videoList.size();

                }
        return size;
    }


}
