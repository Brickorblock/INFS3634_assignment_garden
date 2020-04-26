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
        //Grabbing the video details from the video fragment.
        //Google cloud platform used to generate API key: Console.cloud.google.com. 2020. Google Cloud Platform. [online] Available at: <https://console.cloud.google.com/apis/api/youtube.googleapis.com/overview?project=myproject1-273913&supportedpurview=project> [Accessed 26 April 2020].
        final String Title = bundle.getString("title");
        final String Description = bundle.getString("description");
        //Concatenating strings to make the youtube url
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


        //This creates a new youtube support fragment player using the public API that we created when creating the Youtube Data project on Google cloud.
        //This then replaces the frame layout in the xml with this fragment.

        //Had to download youtube support fragment from the internet as Google's current youtube player support fragment doesn't support androidX:
            //Sakout, M., 2020. Solution: Youtubeplayersupportfragment That Supports Androidx. [online] Gist. Available at: <https://gist.github.com/medyo/f226b967213c3b8ec6f6bebb5338a492> [Accessed 26 April 2020].

        YouTubePlayerSupportFragmentX youtubePlayerFragment = new YouTubePlayerSupportFragmentX();
        //Old Key = AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4
        youtubePlayerFragment.initialize("AIzaSyBtIjC6RTOMN3ZAq_pvpjympgqfjjGCn1s", this);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_fragment, youtubePlayerFragment);

        fragmentTransaction.commit();


        // Inflate the layout for this fragment
        return root;
    }
//This method physically cues the video to be played through using the video ID sent from the video fragment
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
