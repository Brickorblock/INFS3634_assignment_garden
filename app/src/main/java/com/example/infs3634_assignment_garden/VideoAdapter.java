package com.example.infs3634_assignment_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.infs3634_assignment_garden.entities.VideoDetails;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {

    FragmentActivity fragment;
    ArrayList<VideoDetails> videoDetailsArrayList;
    LayoutInflater inflater;


    public VideoAdapter(FragmentActivity fragment, ArrayList<VideoDetails> videoDetailsArrayList) {

        this.fragment = fragment;
        this.videoDetailsArrayList = videoDetailsArrayList;
    }


    @Override
    public int getCount() {
        return this.videoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null) {

            inflater = this.fragment.getLayoutInflater();
        }

        if(convertView == null) {

            convertView = inflater.inflate(R.layout.video_item,null);
        }

      //  ImageView imageView = (ImageView) convertView.findViewById(R.id.imageVid);
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        final VideoDetails videoDetails = (VideoDetails) this.videoDetailsArrayList.get(position);
        LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.root);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle intentBundle = new Bundle();
                intentBundle.putString("videoId", videoDetails.getVideoId());
                intentBundle.putString("Title", videoDetails.getTitle());
                intentBundle.putString("Description", videoDetails.getDescription());
                intentBundle.putString("url", videoDetails.getUrl());

                MainActivity.navController.navigate(R.id.action_videosFragment_to_youtubeFragment, intentBundle);
            }
        });



      //  Picasso.get().load(videoDetails.getUrl()).into(imageView);

        textView.setText(videoDetails.getTitle());

        return convertView;
    }
}
