package com.example.infs3634_assignment_garden.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634_assignment_garden.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

public class YoutubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    TextView VideoTitle;
    TextView VideoDescription;
    TextView Videourl;
    TextView ChannelName;
    Spanned Text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_youtube, container, false);

        Bundle bundle = getArguments();
        //   final String youtubelink = bundle.getString(ChapterFragment.KEY_Youtubelink);

        final String Title = bundle.getString("title");
        final String Description = bundle.getString("description");
        final String url = "https://www.youtube.com/watch?v=" + bundle.getString("videoId");
        final String ChannelTitle = bundle.getString("channeltitle");

        VideoTitle = root.findViewById(R.id.videotitle);
        VideoDescription = root.findViewById(R.id.videodescription);
        Videourl = root.findViewById(R.id.videourl);
        ChannelName = root.findViewById(R.id.channelName);

        Log.d("Youtube Fragment: ", "Channel Name: " + ChannelName);

        VideoTitle.setText(Title);
        VideoDescription.setText(Description);
        ChannelName.setText(ChannelTitle);

        Videourl.setText(url);

        //Upon clicking on the video url, a new implicit intent is created passing in the url of the video chosen.
        //This then opens the youtube application and allows users to view the videos there. 

Videourl.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(webIntent);
    }
});

        //////////////////////////////////////////////////////////////////////////

        YouTubePlayerSupportFragmentX youtubePlayerFragment = new YouTubePlayerSupportFragmentX();
        youtubePlayerFragment.initialize("AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4", this);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_fragment, youtubePlayerFragment);

        fragmentTransaction.commit();


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {

            Bundle bundle = getArguments();
            final String youtubelink = bundle.getString("videoId");
            youTubePlayer.cueVideo(youtubelink);
            Log.d("YouTube Fragment", "Link: " + youtubelink);


            Log.d("Youtube Fragment: ", "Video Cued Successfully!");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
    }
}
